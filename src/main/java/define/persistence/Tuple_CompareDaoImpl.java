package define.persistence;

import define.model.BusinessRule;
import define.model.Constraint;
import define.model.Tuple_Compare;
import persistence.OracleBaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tuple_CompareDaoImpl extends OracleBaseDao implements Tuple_CompareDao {

    private Connection conn;
    private ConstraintDao cdao;
    private BusinessRuleDao bdao;

    public Tuple_CompareDaoImpl() {

        try {
            conn = super.getConnection();
            cdao = new ConstraintOracleDaoImpl();
            bdao = new BusinessRuleOracleDaoImpl();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public Tuple_Compare findByID(int id) {

        try {
            String queryText =  "SELECT NAAM, TABLE_NAME, ATTRIBUTE_NAME, REF_ATTRIBUTE, OPERATOR  " +
                    "FROM CONSTRAINT " +
                    "WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(queryText);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();

            String naam = result.getString("NAAM");
            String table_name = result.getString("TABLE_NAME");
            String attribute_name = result.getString("ATTRIBUTE_NAME");
            String ref_attribute = result.getString("REF_ATTRIBUTE");
            String operator = result.getString("operator");

            return new Tuple_Compare(naam, table_name, id, attribute_name, ref_attribute, operator);

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tuple_Compare save(Tuple_Compare compare) {

        try {
            String queryText =  "INSERT INTO CONSTRAINT(ID, NAAM, TABLE_NAME, ATTRIBUTE_NAME, REF_ATTRIBUTE, OPERATOR, TYPE) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(queryText);

            int id = cdao.createUniqueID();

            String constraintNaam = "BRG_" + compare.getTable().toUpperCase() + "_CNS_TCMP_"+ id;
            String businessruleNaam = "BRG_" + compare.getTable().toUpperCase() + "_CNS_TCMP_"+ id;
            int type = 5;


            stmt.setInt(1, id);
            stmt.setString(2, constraintNaam);
            stmt.setString(3, compare.getTable());
            stmt.setString(4, compare.getAttribute());
            stmt.setString(5, compare.getRef_attribute());
            stmt.setString(6, compare.getOperator());
            stmt.setString(7, "check");

            stmt.executeQuery();

            String queryRule = "INSERT INTO BUSINESSRULE(ID, NAAM, BUSINESSRULETYPEID, CONSTRAINTID) VALUES(?,?,?,?)";

            PreparedStatement stmtrule = conn.prepareStatement(queryRule);

            stmtrule.setInt(1, bdao.createUniqueID());
            stmtrule.setString(2, businessruleNaam);
            stmtrule.setInt(3, type);
            stmtrule.setInt(4, id);

            stmtrule.executeQuery();

            return new Tuple_Compare( constraintNaam, compare.getTable(),id, compare.getAttribute(), compare.getRef_attribute(), compare.getOperator());

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tuple_Compare update(Tuple_Compare compare) {
        return null;
    }

}
