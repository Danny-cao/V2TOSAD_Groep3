package define.persistence;

import define.model.InterEntity_Compare;
import persistence.OracleBaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterEntity_CompareOracleDaoImpl extends OracleBaseDao implements InterEntity_CompareDao {

    private Connection conn;
    private ConstraintDao cdao;
    private BusinessRuleDao bdao;

    public InterEntity_CompareOracleDaoImpl() {
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
    public InterEntity_Compare findByID(int id) {

        try {
            String queryText =  "SELECT * " +
                    "FROM CONSTRAINT " +
                    "WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();
            String naam = result.getString("NAAM");
            String table = result.getString("TABLE_NAME");
            String attribute = result.getString("ATTRIBUTE_NAME");
            String ref_attribute = result.getString("REF_ATTRIBUTE");
            String ref_table = result.getString("REF_TABLE");
            String operator = result.getString("OPERATOR");

            return new InterEntity_Compare(naam, table, id, attribute, ref_table, ref_attribute, operator);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InterEntity_Compare save(InterEntity_Compare compare) {

        try {
            String queryText =  "INSERT INTO CONSTRAINT(ID, NAAM, TABLE_NAME, ATTRIBUTE_NAME, REF_ATTRIBUTE, REF_TABLE ,OPERATOR, TYPE) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(queryText);

            int id = cdao.createUniqueID();

            String constraintNaam = "BRG_" + compare.getTable().toUpperCase() + "_CNS_ICMP_"+ id;
            String businessruleNaam = "BRG_" + compare.getTable().toUpperCase() + "_CNS_ICMP_"+ id;
            int type = 7;

            stmt.setInt(1, id);
            stmt.setString(2, constraintNaam);
            stmt.setString(3, compare.getTable());
            stmt.setString(4, compare.getAttribute());
            stmt.setString(5, compare.getRef_attribute());
            stmt.setString(6, compare.getRef_table());
            stmt.setString(7, compare.getOperator());
            stmt.setString(8, "check");

            stmt.executeQuery();

            String queryRule = "INSERT INTO BUSINESSRULE(ID, NAAM, BUSINESSRULETYPEID, CONSTRAINTID) VALUES(?,?,?,?)";

            PreparedStatement stmtrule = conn.prepareStatement(queryRule);

            stmtrule.setInt(1, bdao.createUniqueID());
            stmtrule.setString(2, businessruleNaam);
            stmtrule.setInt(3, type);
            stmtrule.setInt(4, id);

            stmtrule.executeQuery();

            return new InterEntity_Compare( constraintNaam, compare.getTable(),id, compare.getAttribute(), compare.getRef_attribute(), compare.getRef_table(),compare.getOperator());

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InterEntity_Compare update(InterEntity_Compare compare) {
        return null;
    }
}
