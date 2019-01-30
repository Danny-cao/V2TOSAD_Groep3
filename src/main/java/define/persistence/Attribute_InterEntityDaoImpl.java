package define.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import persistence.OracleBaseDao;

import define.model.Attribute_InterEntity;
//
public class Attribute_InterEntityDaoImpl extends OracleBaseDao implements Attribute_InterEntityDao {

	private BusinessRuleDao bdao = new BusinessRuleOracleDaoImpl();

	public Attribute_InterEntity save(Attribute_InterEntity inter) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();

            int id = bdao.createUniqueID();
            int type = 7;
            String constraintNaam = "BRG_" + inter.getTable().toUpperCase() + "_CNS_ICMP_"+inter.getId();
            String businessruleNaam = "BRG_" + inter.getTable().toUpperCase() + "CNS_ICMP_"+inter.getId();
            
            String query = "INSERT INTO constraint (id, naam,table_name,ref_table,ref_attribute,attribute_name,operator,type)VALUES('" + inter.getId() + "', '"+
                    constraintNaam + "', '" + inter.getTable() + "', '"  + inter.getRef_table()+ "', '" + inter.getAttribute1()+ "', '" + inter.getAttribute2()+ "', '" + inter.getOperator()+ "', '" + "check"  + "')";
            String query1 = "INSERT INTO businessrule (id, naam,businessruletypeid,constraintid)VALUES('" +id + "', '"+
                    businessruleNaam + "', '" + type + "', '" + inter.getId()+ "')";
            
            stmt.executeUpdate(query);
            stmt.executeUpdate(query1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return inter;
    }
	
	public ArrayList<Attribute_InterEntity> selectConstraintInter(String query) {
	    ArrayList<Attribute_InterEntity> results = new ArrayList<Attribute_InterEntity>();
	    try (Connection con = super.getConnection()){
	      Statement stmt = con.createStatement();
	      ResultSet dbResultSet = stmt.executeQuery(query);

	      while (dbResultSet.next()) {
	    	    int constraintnummer = dbResultSet.getInt("id");
		        String naam = dbResultSet.getString("naam");        
		        String table = dbResultSet.getString("table_name");
		        String ref_table = dbResultSet.getString("ref_table");
		        String ref_atribuut = dbResultSet.getString("ref_attribute");
		        String operator = dbResultSet.getString("operator");
		        String attribute = dbResultSet.getString("attribute_name");
		
		       

				 
	 
	        Attribute_InterEntity beperking = new Attribute_InterEntity(table,naam,constraintnummer,ref_table,ref_atribuut,operator,attribute);
	       
	        
	        results.add(beperking);
	      }
	    } catch (SQLException sqle) { sqle.printStackTrace(); }
	    
	    return results;
	  }
	
	// voor update interEntity rule 
	public ArrayList<Attribute_InterEntity> findAllInter() { 
	    return selectConstraintInter("SELECT * from constraint"); 
	  }
	
	public boolean updateInter (Attribute_InterEntity inter) throws SQLException {
		Connection c = super.getConnection();
		PreparedStatement ps = c.prepareStatement("UPDATE constraint SET naam=? table_name = ?,ref_table = ?, ref_attribute = ?, operator = ?, attribute_name =? WHERE id = ?");
		PreparedStatement ps1 = c.prepareStatement("UPDATE businessrule SET naam = ? WHERE constraintid = ?");
		ps.setString(1,"BRG_VBMG_" + inter.getTable().toUpperCase() + "_CNS_ICMP_"+inter.getId());
		ps.setString(2, inter.getTable());
		ps.setString(3, inter.getRef_table());
		ps.setString(4, inter.getAttribute1());
		ps.setString(5, inter.getOperator());
		ps.setString(6, inter.getAttribute2());
		ps.setDouble(7, inter.getId());
		ps1.setString(1,"BRG_VBMG_" + inter.getTable().toUpperCase() + "_ICMP_"+inter.getId());
		ps1.setInt(2,inter.getId());
		boolean result = ps.executeUpdate() > 0;
		boolean result1 = ps1.executeUpdate() > 0;
        ps.close();
        c.close();
        return result;
		
	
	}
	
	

}
