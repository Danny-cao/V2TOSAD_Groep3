package define.persistence;

import define.model.Attribute_Compare;
import persistence.OracleBaseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConstraintDao extends OracleBaseDao {
    public Attribute_Compare save(Attribute_Compare compare) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO constraint (id, naam, table_name ,ref_attribute, operator, value)VALUES(2, '" +
                    compare.getNaam() + "', '" + compare.getTable() + "', '" + compare.getAttribute() + "', '" + compare.getOperator() + "', '" + compare.getValue()+ "')";
            stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return compare;
    }

}
