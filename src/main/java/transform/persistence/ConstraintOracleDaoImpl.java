package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.Constraint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConstraintOracleDaoImpl extends OracleBaseDao implements ConstraintDao {
    private Connection conn;

    public ConstraintOracleDaoImpl() {
        try {
            conn = super.getConnection();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public Constraint findAll() {
        return null;
    }

    @Override
    public Constraint findByID(int id) {

        try {
            String queryText =  "SELECT ID, NAAM" +
                    "FROM Constraint " +
                    "WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryText);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();

            //
            String naam = result.getString("NAAM");
            String table = "test";


            return new Constraint(naam, table, id);

        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
