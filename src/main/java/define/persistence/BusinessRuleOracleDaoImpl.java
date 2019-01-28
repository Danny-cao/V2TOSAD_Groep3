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
            tdao = new BusinessRuleTypeOracleDaoImpl();
            cdao = new ConstraintOracleDaoImpl();
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
    public BusinessRule getBusinessRule(String naam, String table, int type) {

        try {

            String queryText = "select BUSINESSRULE.ID as busi_id, Businessruletypeid, constraintid " +
                    "from BUSINESSRULE " +
                    "join Businessruletype on BUSINESSRULE.BUSINESSRULETYPEID = Businessruletype.id " +
                    "join constraint on BUSINESSRULE.CONSTRAINTID = CONSTRAINT.ID " +
                    "where businessrule.naam = ? " +
                    "and CONSTRAINT.TABLE_NAME = ? " +
                    "and BUSINESSRULETYPE.ID = ? ";


            PreparedStatement stmt = conn.prepareStatement(queryText);

            stmt.setString(1, naam);
            stmt.setString(2, table);
            stmt.setInt(3, type);

            ResultSet result = stmt.executeQuery();

            result.next();

            int id = result.getInt("busi_id");
            System.out.println("impl id:" + id);
            int businessruletypeid = result.getInt("BUSINESSRULETYPEID");
            System.out.println("type id:" + businessruletypeid);
            int constraintid = result.getInt("CONSTRAINTID");
            System.out.println("constraint id:" + constraintid);

            BusinessRuleType businessRuleType = tdao.getBusinessRuleTypeByID(businessruletypeid);
            Constraint constraint = cdao.getConstraintByID(constraintid);

            return new BusinessRule(id, naam, businessRuleType, constraint);

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
