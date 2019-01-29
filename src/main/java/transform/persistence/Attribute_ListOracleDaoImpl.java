package transform.persistence;

import persistence.OracleBaseDao;
import transform.model.Attribute_Compare;
import transform.model.Attribute_List;
import transform.model.BusinessRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Attribute_ListOracleDaoImpl extends OracleBaseDao implements Attribute_ListDao {

    private Connection conn;

    public Attribute_ListOracleDaoImpl() {
        try{
            conn = super.getConnection();
        }
        catch(SQLException e){
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }


    public Attribute_List getAttribute_List(BusinessRule rule) {

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
            String operator = result.getString("OPERATOR");
            String value = result.getString("VALUE");
            String value2 = result.getString("VALUE2");

            String[] valueList = value2.split(",");

            List<String> values = new ArrayList<>(Arrays.asList(valueList));

            return new Attribute_List(id, naam, table, value, operator, values);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
