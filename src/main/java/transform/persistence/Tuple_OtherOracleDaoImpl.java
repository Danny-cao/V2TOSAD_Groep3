package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.Attribute_Compare;
import transform.model.BusinessRule;
import transform.model.Tuple_Other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tuple_OtherOracleDaoImpl extends OracleBaseDao implements Tuple_OtherDao {

    private Connection conn;

    public Tuple_OtherOracleDaoImpl() {
        try{
            conn = super.getConnection();
        }
        catch(SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    public Tuple_Other findByID(int id) {

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
            String value = result.getString("VALUE");

            return new Tuple_Other(naam, table, id, value);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Tuple_Other getTuple_Other(BusinessRule rule) {

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
            String value = result.getString("VALUE");

            return new Tuple_Other(naam, table, id, value);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
