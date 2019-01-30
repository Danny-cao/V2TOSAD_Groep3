package define.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import define.model.Attribute_Range;
//
public interface Attribute_RangeDao {
	public Attribute_Range save(Attribute_Range range);
	public ArrayList<Attribute_Range> selectConstraintRange(String query);
	public ArrayList<Attribute_Range> findAllRange();
	public boolean updateRange(Attribute_Range range) throws SQLException;
	
}
