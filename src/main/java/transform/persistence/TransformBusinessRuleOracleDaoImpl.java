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
            aldao = new Attribute_ListOracleDaoImpl();
            aodao = new Attribute_OtherOracleDaoImpl();
            acdao = new Attribute_CompareOracleDaoImpl();
            tcdao = new Tuple_CompareOracleDaoImpl();
            todao = new Tuple_OtherOracleDaoImpl();
            iecdao = new InterEntity_CompareDaoOracleImpl();
            eodao = new Entity_OtherOracleDaoImpl();

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

            Attribute_List list = aldao.getAttribute_List(rule);

            String generatedCode = GenerateAttributeList(list);
            System.out.println(generatedCode);

            return true;
            //return transformDatabase(generatedCode);

        } else if (rule.getType().equals("Attribute_Other")) {

            Attribute_Other other = aodao.getAttribute_Other(rule);

            String generatedCode = GenerateAttributeOther(other);
            System.out.println(generatedCode);

            return true;
            //return transformDatabase(generatedCode);

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

        } else if (rule.getType().getNaam().equals("Tuple Other rule")) {

            Tuple_Other other = todao.getTuple_Other(rule);

            String generatedCode = GeneratedTupleOther(other);
            System.out.println(generatedCode);

            return true;
            //return transformDatabase(generatedCode);

        } else if (rule.getType().getNaam().equals("Inter-Entity Compare rule")){

            InterEntity_Compare compare = iecdao.getInterEntityCompare(rule);

            String generatedCode = GenerateInterEntityCompare(compare);
            System.out.println(generatedCode);

            return true;
            //return transformDatabase(generatedCode);

        } else if (rule.getType().getNaam().equals("Entity Other rule")) {

            Entity_Other other = eodao.getEntityOther(rule);

            String generatedCode = GenerateEntityOther(other);
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
    public String GenerateAttributeList(Attribute_List list) {

        String table = list.getTable();
        String name = list.getNaam();
        String value = list.getValue();
        List<String> valuesList = list.getValues();

        String values = "(";

        for (String x : valuesList) {

            String v = "'" + x + "'";
            values += "'" + v + "'";
            values += ",";

        }

        values.substring(0,values.length()-1);
        values += ")";

        if (list.getInList().equals("yes")) {

            String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + value + " IN " + values + ")";
            return query;

        } else {

            String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + value + " NOT IN " + values + ")";
            return query;

        }

    }

    @Override
    public String GenerateAttributeOther(Attribute_Other other) {

        String table = other.getTable();
        String name = other.getNaam();
        String constraintValue = other.getConstraint();

        String query = "ALTER TABLE " + table + " ADD CONSTRAINT " + name + " CHECK (" + constraintValue + ")";
        return query;

    }

    @Override
    public String GenerateTupleCompare(Tuple_Compare compare) {

        String operator = compare.getOperator();

        String query = "ALTER TABLE " + compare.getTable() + " ADD CONSTRAINT " + compare.getNaam() + " CHECK (" + compare.getAttribute() + " " + operator + " " + compare.getRef_attribute() + ");";
        return query;
    }

    @Override
    public String GenerateTupleOther(Tuple_Other other) {



    }

    @Override
    public String GenerateInterEntityCompare(InterEntity_Compare compare) {

        String operator = compare.getOperator();
        String query = "ALTER TABLE " + compare.getTable() + " ADD CONSTRAINT " + compare.getNaam() + " CHECK (" + compare.getTable() + "." + compare.getAttribute() + " " + operator + " " + compare.getRef_table() + "." + compare.getRef_attribute() + ");";
        return query;
    }

    @Override
    public String GenerateEntityOther(Entity_Other other) {



    }

}
