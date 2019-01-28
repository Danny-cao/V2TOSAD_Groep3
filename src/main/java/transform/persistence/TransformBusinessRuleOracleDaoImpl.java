package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransformBusinessRuleOracleDaoImpl extends OracleBaseDao implements TransformBusinessRuleDao {
    private Attribute_RangeDao adao;
    private Attribute_CompareDao acdao;
    private Tuple_CompareDao tcdao;
    private InterEntity_CompareDao iecdao;
    private Connection conn;

    public TransformBusinessRuleOracleDaoImpl() {
        try {
            conn = super.getConnection();
            adao = new Attribute_RangeOracleDaoImpl();
            acdao = new Attribute_CompareOracleDaoImpl();
            tcdao = new Tuple_CompareOracleDaoImpl();
            iecdao = new InterEntity_CompareDaoOracleImpl();

        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    public boolean transformDatabase(String query){

        try {

            PreparedStatement stmt = conn.prepareStatement(query);
            int result = stmt.executeUpdate();

            if (result > 0) {

                return true;

            } else {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean transform(BusinessRule rule) {

        if (rule.getType().getNaam().equals("Attribute Compare rule")) {

            Attribute_Compare compare = acdao.getAttribute_Compare(rule);

            String generatedCode = GenerateAttributeCompare(compare);

            System.out.println(generatedCode);
            return true;

            //return transformDatabase(generatedCode);

        } else if (rule.getType().equals("Attribute_List")) {

            Attribute_List constraint = ((Attribute_List) rule.getConstraint());

            String table = constraint.getTable();
            String name = constraint.getNaam();
            String value = constraint.getValue();
            List<String> valuesList = constraint.getValues();

            String values = "(";

            for (String x : valuesList) {

                String v = "'" + x + "'";
                values += "'" + v + "'";
                values += ",";

            }

            values.substring(0,values.length()-1);
            values += ")";

            if (constraint.getInList().equals("yes")) {

                String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + value + " IN " + values + ")";
                return transformDatabase(query);

            } else {

                String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + value + " NOT IN " + values + ")";
                return transformDatabase(query);

            }

        } else if (rule.getType().equals("Attribute_Other")) {

            Attribute_Other constraint = ((Attribute_Other) rule.getConstraint());

            String query = "";

            return transformDatabase(query);

        } else if (rule.getType().getNaam().equals("Attribute Range rule")) {

            Attribute_Range range = adao.getAttribute_Range(rule);

            String generatedCode = GenerateAttributeRange(range);

            System.out.println(generatedCode);
            return true;
            //return transformDatabase(generatedCode);

        } else if (rule.getType().getNaam().equals("Tuple Compare rule")){

            Tuple_Compare compare = tcdao.getTuple_Compare(rule);

            String generatedCode = GenerateTupleCompare(compare);
            System.out.println(generatedCode);

            return true;
            //return transformDatabase(generatedCode);

        } else if (rule.getType().getNaam().equals("Inter-Entity Compare rule")){

            InterEntity_Compare compare = iecdao.getInterEntityCompare(rule);

            String generatedCode = GenerateInterEntityCompare(compare);
            System.out.println(generatedCode);

            return true;
            //return transformDatabase(generatedCode);

        } else {

            return false;

        }
    }

    @Override
    public String GenerateAttributeRange(Attribute_Range range) {

        if (range.getOperator().equals("between")) {

            String query = "ALTER TABLE " + range.getTable() + " ADD CONSTRAINT " + range.getNaam() + " CHECK (" + range.getAttribute() + " > " + range.getValue1() + " AND " + range.getAttribute() + " < " + range.getValue2() + ");";
            return query;

        } else {

            String query = "ALTER TABLE " + range.getTable() + " ADD CONSTRAINT " + range.getNaam() + " CHECK (" + range.getAttribute() + " < " + range.getValue1() + " AND " + range.getAttribute() + " > " + range.getValue2() + ");";
            return query;
        }
    }

    @Override
    public String GenerateAttributeCompare(Attribute_Compare compare) {

        String operator = compare.getOperator();

        String query = "ALTER TABLE " + compare.getTable() + " ADD CONSTRAINT " + compare.getNaam() + " CHECK (" + compare.getAttribute() + " " + operator + " " + compare.getValue() + ");";
        return query;


    }

    @Override
    public String GenerateTupleCompare(Tuple_Compare compare) {

        String operator = compare.getOperator();

        String query = "ALTER TABLE " + compare.getTable() + " ADD CONSTRAINT " + compare.getNaam() + " CHECK (" + compare.getAttribute() + " " + operator + " " + compare.getRef_attribute() + ");";
        return query;
    }

    @Override
    public String GenerateInterEntityCompare(InterEntity_Compare compare) {

        String operator = compare.getOperator();
        String query = "ALTER TABLE " + compare.getTable() + " ADD CONSTRAINT " + compare.getNaam() + " CHECK (" + compare.getTable() + "." + compare.getAttribute() + " " + operator + " " + compare.getRef_table() + "." + compare.getRef_attribute() + ");";
        return query;
    }
}
