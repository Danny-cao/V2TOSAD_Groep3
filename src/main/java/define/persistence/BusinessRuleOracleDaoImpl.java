package define.persistence;

import define.model.BusinessRuleType;
import persistence.OracleBaseDao;
import define.model.BusinessRule;
import define.model.Constraint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessRuleOracleDaoImpl extends OracleBaseDao implements BusinessRuleDao {

    private Connection conn;
    private BusinessRuleTypeDao tdao;
    private ConstraintDao cdao;

    public BusinessRuleOracleDaoImpl() {
        try {
            conn = super.getConnection();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public BusinessRule getBusinessRuleById(int id) {
        try {
            String queryText =  "SELECT ID, NAAM, BUSINESSRULETYPEID, CONSTRAINTID " +
                    "FROM BUSINESSRULE " +
                    "WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(queryText);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();

            String naam = result.getString("NAAM");
            int businessruletypeid = result.getInt("BUSINESSRULETYPEID");
            int constraintid = result.getInt("CONSTRAINTID");

            BusinessRuleType businessRuleType = tdao.getBusinessRuleTypeByID(businessruletypeid);
            Constraint constraint = cdao.getConstraintByID(constraintid);


            return new BusinessRule(id, naam, businessRuleType, constraint);

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BusinessRule getBusinessRule(String naam, String table, BusinessRuleType type) {
        return null;
    }

}
