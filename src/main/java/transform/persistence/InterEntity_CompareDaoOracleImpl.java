package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.BusinessRule;
import transform.model.InterEntity_Compare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterEntity_CompareDaoOracleImpl extends OracleBaseDao implements InterEntity_CompareDao {

    private Connection conn;

    public InterEntity_CompareDaoOracleImpl() {
        try{
            conn = super.getConnection();
        }
        catch(SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

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
    public InterEntity_Compare getInterEntityCompare(BusinessRule rule) {
        try {
            String queryText =  "SELECT * " +
                    "FROM CONSTRAINT " +
                    "WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, rule.getConstraint().getId());
            ResultSet result = stmt.executeQuery();

            result.next();
            String naam = result.getString("NAAM");
            String table = result.getString("TABLE_NAME");
            int id = rule.getConstraint().getId();
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
}
