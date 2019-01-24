package define.persistence;

import define.model.BusinessRule;
import persistence.OracleBaseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BusinessRuleDao extends OracleBaseDao {
    public BusinessRule save(BusinessRule rule) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO businessrule (id, naam,businessruletypeid,constraintid)VALUES(2, '" +
                    rule.getNaam() + "', '" + rule.getType() + "', '" + rule.getConstraint()+ "')";
            stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return rule;
    }

}

