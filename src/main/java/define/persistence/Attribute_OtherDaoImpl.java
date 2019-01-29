package define.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistence.OracleBaseDao;

import define.model.Attribute_Other;

public class Attribute_OtherDaoImpl extends OracleBaseDao implements Attribute_OtherDao {
	
	public Attribute_Other save(Attribute_Other other) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            int id = 30;
            int type = 4;
            String constraintNaam = "BRG_VBMG_" + other.getTable().toUpperCase() + "_CNS_TCMP_"+"01"; 
            String businessruleNaam = "BRG_VBMG_" + other.getTable().toUpperCase() + "_TCMP_"+"01"; 
            
            String query = "INSERT INTO constraint (id, naam, table_name,ref_attribute,attribute_name,operator,type)VALUES('" + other.getId() + "', '"+
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
		PreparedStatement ps = c.prepareStatement("UPDATE constraint SET table_name = ?, ref_attribute = ?, operator = ?, attribute_name =? WHERE id = ?");
		ps.setString(1, other.getTable());
		ps.setString(2, other.getAttribute1());
		ps.setString(3, other.getOperator());
		ps.setString(4, other.getAttribute2());
		ps.setDouble(5, other.getId());
		boolean result = ps.executeUpdate() > 0;
        ps.close();
        c.close();
        return result;
		
	
	}
	
	

}
