package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.Attribute_Compare;
import transform.model.BusinessRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attribute_CompareOracleDaoImpl extends OracleBaseDao implements Attribute_CompareDao {

    private Connection conn;

    public Attribute_CompareOracleDaoImpl() {
        try{
            conn = super.getConnection();
        }
        catch(SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public Attribute_Compare getAttribute_Compare(BusinessRule rule) {
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
            String attribute = result.getString("REF_ATTRIBUTE");
            String operator = result.getString("OPERATOR");
            double value = result.getDouble("VALUE");

            return new Attribute_Compare(naam, table, id, attribute, value, operator);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
