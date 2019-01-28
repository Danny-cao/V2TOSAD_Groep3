package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.BusinessRule;
import transform.model.BusinessRuleType;
import transform.model.Constraint;

import java.sql.*;
import java.util.ArrayList;
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
    public ArrayList<BusinessRule> findAll() {

        return null;
    }

    @Override
    public BusinessRule executeQuery(String query) {
        return null;
    }

    @Override
    public BusinessRule findByID(int id) {
        try {
            String queryText =  "SELECT ID, NAAM, BUSINESSRULETYPEID, CONSTRAINTID " +
                    "FROM BUSINESSRULE " +
                    "WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(queryText);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();

            //
            String naam = result.getString("NAAM");
            int businessruletypeid = result.getInt("BUSINESSRULETYPEID");
            int constraintid = result.getInt("CONSTRAINTID");

            BusinessRuleType businessRuleType = tdao.findByID(businessruletypeid);
            Constraint constraint = cdao.findByID(constraintid);


            return new BusinessRule(id, naam, businessRuleType, constraint);

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(BusinessRule businessRule) {
        try {
            String query =  "INSERT INTO BUSINESSRULE (id, naam, businessruletypeid, constraintid)" +
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
                    "values  (?);";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, businessRule.getNaam());

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

    @Override
    public ArrayList<BusinessRule> selectBusinessRules(String query) {
        ArrayList<BusinessRule> resultaten = new ArrayList<BusinessRule>();
        try (Connection c = super.getConnection()) {
            Statement pstmt = c.createStatement();
            ResultSet dbResultSet = pstmt.executeQuery(query);

            while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                String naam = dbResultSet.getString("naam");

                BusinessRule businessrule = new BusinessRule(id, naam);

                resultaten.add(businessrule);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();

        }
        return resultaten;
    }
}

