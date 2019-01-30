package define.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import define.model.BusinessRule;
import persistence.OracleBaseDao;

import define.model.Attribute_Range;

public class Attribute_RangeDaoImpl extends OracleBaseDao implements Attribute_RangeDao {

	private ConstraintDao cdao = new ConstraintOracleDaoImpl();
	private BusinessRuleDao bdao = new BusinessRuleOracleDaoImpl();
	
	public Attribute_Range save(Attribute_Range range) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            int id = bdao.createUniqueID();
            int type = 1;
            String constraintNaam = "BRG_VBMG_" + range.getTable().toUpperCase() + "_CNS_ARNG_"+range.getId();
            String businessruleNaam = "BRG_VBMG_" + range.getTable().toUpperCase() + "_CNS_ARNG_"+range.getId();
            
            String query = "INSERT INTO constraint (id, naam, table_name ,attribute_name, operator, value,value2,type)VALUES('" + range.getId() + "', '"+
                    constraintNaam + "', '" + range.getTable()  + "', '" + range.getAttribute() + "', '" + range.getOperator() + "', '" + range.getValue1()+"', '" + range.getValue2()+ "', '" + "check"  + "')";
            String query1 = "INSERT INTO businessrule (id, naam,businessruletypeid,constraintid)VALUES('" +id + "', '"+
                    businessruleNaam + "', '" + type + "', '" + range.getId()+ "')";
            
            stmt.executeUpdate(query);
            stmt.executeUpdate(query1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return range;
    }
	
	public ArrayList<Attribute_Range> selectConstraintRange(String query) {
	    ArrayList<Attribute_Range> results = new ArrayList<Attribute_Range>();
	    try (Connection con = super.getConnection()){
	      Statement stmt = con.createStatement();
	      ResultSet dbResultSet = stmt.executeQuery(query);

	      while (dbResultSet.next()) {
	    	    int constraintnummer = dbResultSet.getInt("id");
		        String naam = dbResultSet.getString("naam");        
		        String table = dbResultSet.getString("table_name");
		        String atribuut = dbResultSet.getString("ref_attribute");
		        String operator = dbResultSet.getString("operator");
		        int value1 = dbResultSet.getInt("value");
		        int value2 = dbResultSet.getInt("value2");
		       

				 
	 
	        Attribute_Range beperking = new Attribute_Range(table,naam,constraintnummer,atribuut,value2,value1,operator);
	       
	        
	        results.add(beperking);
	      }
	    } catch (SQLException sqle) { sqle.printStackTrace(); }
	    
	    return results;
	  }
	
	public ArrayList<Attribute_Range> findAllRange() { 
	    return selectConstraintRange("SELECT * from constraint"); 
	  }
	
			
	public boolean updateRange (Attribute_Range range) throws SQLException {
				Connection c = super.getConnection();
				PreparedStatement ps = c.prepareStatement("UPDATE constraint SET table_name = ?, ref_attribute = ?, operator = ?, value = ?,value2=? WHERE id = ?");
				ps.setString(1, range.getTable());
				ps.setString(2, range.getAttribute());
				ps.setString(3, range.getOperator());
				ps.setDouble(4, range.getValue1());
				ps.setDouble(5, range.getValue2());
				ps.setInt(6,range.getId());
				boolean result = ps.executeUpdate() > 0;
		        ps.close();
		        c.close();
		        return result;
				
			
			}

	

		

}
