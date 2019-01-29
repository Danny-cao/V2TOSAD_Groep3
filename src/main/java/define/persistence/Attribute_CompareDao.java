package define.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import define.model.Attribute_Compare;

public interface Attribute_CompareDao {
	public Attribute_Compare save(Attribute_Compare compare);
	public ArrayList<Attribute_Compare> selectConstraintCompare(String query);
	public boolean updateCompare(Attribute_Compare compare) throws SQLException;
	public ArrayList<Attribute_Compare> findAllCompare();
	
	
	

}
