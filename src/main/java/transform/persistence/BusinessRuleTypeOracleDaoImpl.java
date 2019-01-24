package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.BusinessRuleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<BusinessRuleType> findAll() {
        try {
            List<BusinessRuleType> list = new ArrayList<>();
            String queryText =  "SELECT NAAM, CATEGORIE " +
                                "FROM BUSINESSRULETYPE";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("ID");
                String naam = result.getString("NAAM");
                String categorie = result.getString("CATEGORIE");
                list.add(new BusinessRuleType(id, naam, categorie));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BusinessRuleType findByID(int id) {
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

            return new BusinessRuleType(id, naam, categorie);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
