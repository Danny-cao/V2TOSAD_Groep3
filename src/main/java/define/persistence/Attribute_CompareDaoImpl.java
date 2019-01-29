package define.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import define.model.Attribute_Compare;
import persistence.OracleBaseDao;


public class Attribute_CompareDaoImpl extends OracleBaseDao implements Attribute_CompareDao {

	public Attribute_Compare save(Attribute_Compare compare) {
		try (Connection con = getConnection()) {
			Statement stmt = con.createStatement();


			int id = 28;
			int type = 2;
			String constraintNaam = "BRG_VBMG_" + compare.getTable().toUpperCase() + "_CNS_ACMP_"+"01";
			String businessruleNaam = "BRG_VBMG_" + compare.getTable().toUpperCase() + "_ACMP_"+"01";
			System.out.println(businessruleNaam);

			String query = "INSERT INTO constraint (id, naam, table_name ,ref_attribute, operator, value,type)VALUES('" + compare.getId() + "', '"+
					constraintNaam + "', '" + compare.getTable() +  "', '" + compare.getAttribute() + "', '" + compare.getOperator() + "', '" + compare.getValue()+ "', '" + "check"  + "')";
			String query1 = "INSERT INTO businessrule (id, naam,businessruletypeid,constraintid)VALUES('" +id + "', '"+
					businessruleNaam + "', '" + type + "', '" + compare.getId()+ "')";

			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return compare;
	}

	public ArrayList<Attribute_Compare> selectConstraintCompare(String query) {
		ArrayList<Attribute_Compare> results = new ArrayList<Attribute_Compare>();
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int constraintnummer = dbResultSet.getInt("id");
				String naam = dbResultSet.getString("naam");
				String table = dbResultSet.getString("table_name");
				String atribuut = dbResultSet.getString("ref_attribute");
				String operator = dbResultSet.getString("operator");
				String value = dbResultSet.getString("value");




				Attribute_Compare beperking = new Attribute_Compare(table,naam,constraintnummer,atribuut,operator,value);


				results.add(beperking);
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }

		return results;
	}

	public boolean updateCompare (Attribute_Compare compare) throws SQLException {
		Connection c = super.getConnection();
		PreparedStatement ps = c.prepareStatement("UPDATE constraint SET table_name = ?, ref_attribute = ?, operator = ?, value = ? WHERE id = ?");
		ps.setString(1, compare.getTable());
		ps.setString(2, compare.getAttribute());
		ps.setString(3, compare.getOperator());
		ps.setString(4, compare.getValue());
		ps.setInt(5,compare.getId());
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		c.close();
		return result;


	}

	public ArrayList<Attribute_Compare> findAllCompare() {
		return selectConstraintCompare("SELECT * from constraint");
	}

}
