package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.Constraint;

import java.sql.Connection;
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
}
