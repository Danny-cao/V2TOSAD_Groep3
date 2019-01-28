package define.persistence;

import define.model.BusinessRuleType;
import persistence.OracleBaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessRuleTypeOracleDaoImpl extends OracleBaseDao implements BusinessRuleTypeDao {

    private Connection conn;

    public BusinessRuleTypeOracleDaoImpl() {
        try {
            conn = super.getConnection();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public BusinessRuleType getBusinessRuleTypeByID(int id) {
        try {
            String queryText =  "SELECT NAAM, CATEGORIE " +
                    "FROM BUSINESSRULETYPE " +
                    "WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();
            String naam = result.getString("NAAM");
            String categorie = result.getString("CATEGORIE");

            System.out.println("type impl" + id + naam + categorie);
            return new BusinessRuleType(id, naam, categorie);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(BusinessRuleType type) {

        boolean success = false;

        try{

            String queryText = "DELETE " +
                    "FROM BUSINESSRULETYPE " +
                    "WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, type.getId());

            if(stmt.executeUpdate() > 0) {
                success = true;
            }

            return success;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
