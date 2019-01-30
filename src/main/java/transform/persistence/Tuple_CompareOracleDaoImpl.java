package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.BusinessRule;
import transform.model.Tuple_Compare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tuple_CompareOracleDaoImpl extends OracleBaseDao implements Tuple_CompareDao {

    private Connection conn;

    public Tuple_CompareOracleDaoImpl() {
        try{
            conn = super.getConnection();
        }
        catch(SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    public Tuple_Compare findByID(int id) {

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
            String ref_attribute = result.getString("REF_ATTRIBUTE");
            String attribute = result.getString("ATTRIBUTE_NAME");
            String operator = result.getString("OPERATOR");

            return new Tuple_Compare(naam, table, id, attribute, ref_attribute, operator);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Tuple_Compare getTuple_Compare(BusinessRule rule) {
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
            String ref_attribute = result.getString("REF_ATTRIBUTE");
            String attribute = result.getString("ATTRIBUTE_NAME");
            String operator = result.getString("OPERATOR");

            return new Tuple_Compare(naam, table, id, attribute, ref_attribute, operator);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
