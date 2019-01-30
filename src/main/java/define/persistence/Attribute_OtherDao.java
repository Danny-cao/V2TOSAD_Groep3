package define.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import define.model.Attribute_Other;

public interface Attribute_OtherDao {
	
	Attribute_Other save(Attribute_Other other);
	ArrayList<Attribute_Other> selectConstraintOther(String query);
	ArrayList<Attribute_Other> findAllOther();
	boolean updateOther(Attribute_Other other) throws SQLException;


}
