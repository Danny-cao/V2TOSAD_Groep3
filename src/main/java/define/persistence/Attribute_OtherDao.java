package define.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import define.model.Attribute_Other;

public interface Attribute_OtherDao {
	
	public Attribute_Other save(Attribute_Other other);
	public ArrayList<Attribute_Other> selectConstraintOther(String query);
	public ArrayList<Attribute_Other> findAllOther();
	public boolean updateOther(Attribute_Other other) throws SQLException;


}
