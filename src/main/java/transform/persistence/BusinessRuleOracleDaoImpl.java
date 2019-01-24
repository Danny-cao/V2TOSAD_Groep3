package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.BusinessRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BusinessRuleOracleDaoImpl extends OracleBaseDao implements BusinessRuleDao {
    private ConstraintDao cdao;
    private BusinessRuleTypeDao tdao;
    private Connection conn;

    public BusinessRuleOracleDaoImpl() {
        try {
            conn = super.getConnection();
            cdao = new ConstraintOracleDaoImpl();
            tdao = new BusinessRuleTypeOracleDaoImpl();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public List<BusinessRule> findAll() {

        return null;
    }

    @Override
    public BusinessRule findByID(int id) {
        return null;
    }

    @Override
    public boolean save(BusinessRule businessRule) {
        try {
            String query = "INSERT INTO BUSINESSRULE (id, naam, businessruletypeid, constraintid)" +
                    "VALUES (?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, businessRule.getId());
            stmt.setString(2, businessRule.getNaam());
            stmt.setInt(3, businessRule.getType().getId());
            stmt.setInt(4, businessRule.getConstraint().getId());

            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BusinessRule businessRule) {
        try {
            String query = "UPDATE BUSINESSRULE " +
                    "set naam = ? " +
                    "where ID = ?;";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, businessRule.getNaam());
            stmt.setInt(2, businessRule.getId());

            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(BusinessRule businessRule) {
        try {
            String query = "delete from BUSINESSRULE where ID = ?;";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, businessRule.getId());

            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
