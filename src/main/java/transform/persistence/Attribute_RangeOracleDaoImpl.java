package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.Attribute_Range;
import transform.model.BusinessRule;
import transform.model.BusinessRuleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Attribute_RangeOracleDaoImpl extends OracleBaseDao implements Attribute_RangeDao {

    private Connection conn;

    public Attribute_RangeOracleDaoImpl(){
        try{
            conn = super.getConnection();
        } catch(SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    public Attribute_Range findByID(int id) {

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
            String operator = result.getString("OPERATOR");
            double value = result.getDouble("VALUE");
            double value2 = result.getDouble("VALUE2");

            return new Attribute_Range(naam, table, id, attribute, operator, value, value2);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Attribute_Range getAttribute_Range(BusinessRule rule) {

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
            int id = result.getInt("ID");
            String attribute = result.getString("ATTRIBUTE_NAME");
            String operator = result.getString("OPERATOR");
            double value = result.getDouble("VALUE");
            double value2 = result.getDouble("VALUE2");

            return new Attribute_Range(naam, table, id, attribute, operator, value, value2);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
