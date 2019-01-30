package define.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistence.OracleBaseDao;

import define.model.Attribute_Other;
//
public class Attribute_OtherDaoImpl extends OracleBaseDao implements Attribute_OtherDao {
	
	public Attribute_Other save(Attribute_Other other) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            int id = 77;
            int type = 4;
            String constraintNaam = "BRG_VBMG_" + other.getTable().toUpperCase() + "_CNS_TCMP_"+other.getId();
            String businessruleNaam = "BRG_VBMG_" + other.getTable().toUpperCase() + "_TCMP_"+other.getId();
            
            String query = "INSERT INTO constraint (id, naam, table_name,attribute_name,ref_attribute,operator,type)VALUES('" + other.getId() + "', '"+
                    constraintNaam + "', '" + other.getTable() + "', '"  + other.getAttribute1()+ "', '" + other.getAttribute2() + "', '" + other.getOperator()+ "', '" + "check"  + "')";
            String query1 = "INSERT INTO businessrule (id, naam,businessruletypeid,constraintid)VALUES('" +id + "', '"+
                    businessruleNaam + "', '" + type + "', '" + other.getId()+ "')";
            
            stmt.executeUpdate(query);
            stmt.executeUpdate(query1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return other;
    }
	
	public ArrayList<Attribute_Other> selectConstraintOther(String query) {
	    ArrayList<Attribute_Other> results = new ArrayList<Attribute_Other>();
	    try (Connection con = super.getConnection()){
	      Statement stmt = con.createStatement();
	      ResultSet dbResultSet = stmt.executeQuery(query);

	      while (dbResultSet.next()) {
	    	    int constraintnummer = dbResultSet.getInt("id");
		        String naam = dbResultSet.getString("naam");        
		        String table = dbResultSet.getString("table_name");
		        String ref_atribuut = dbResultSet.getString("ref_attribute");
		        String operator = dbResultSet.getString("operator");
		        String attribute = dbResultSet.getString("attribute_name");
		
		       

				 
	 
	        Attribute_Other beperking = new Attribute_Other(table,naam,constraintnummer,ref_atribuut,attribute,operator);
	       
	        
	        results.add(beperking);
	      }
	    } catch (SQLException sqle) { sqle.printStackTrace(); }
	    
	    return results;
	  }
	
	public ArrayList<Attribute_Other> findAllOther() { 
	    return selectConstraintOther("SELECT * from constraint"); 
	  }
	

	
	public boolean updateOther (Attribute_Other other) throws SQLException {
		Connection c = super.getConnection();
		PreparedStatement ps = c.prepareStatement("UPDATE constraint SET naam=?,table_name = ?, ref_attribute = ?, operator = ?, attribute_name =? WHERE id = ?");
		PreparedStatement ps1 = c.prepareStatement("UPDATE businessrule SET naam=? WHERE constraintid = ?");
		ps.setString(1, "BRG_VBMG_" + other.getTable().toUpperCase() + "_CNS_TCMP_"+other.getId());
		ps.setString(2, other.getTable());
		ps.setString(3, other.getAttribute1());
		ps.setString(4, other.getOperator());
		ps.setString(5, other.getAttribute2());
		ps.setDouble(6, other.getId());
		ps1.setString(1,"BRG_VBMG_" + other.getTable().toUpperCase() + "_TCMP_"+other.getId());
		ps1.setInt(2,other.getId());
		boolean result = ps.executeUpdate() > 0;
		boolean result1 = ps1.executeUpdate() > 0;
        ps.close();
        c.close();
        return result;
		
	
	}
	
	

}
