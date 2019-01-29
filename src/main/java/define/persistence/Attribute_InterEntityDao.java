package define.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import define.model.Attribute_InterEntity;


public interface Attribute_InterEntityDao {
	public Attribute_InterEntity save(Attribute_InterEntity inter);
	public ArrayList<Attribute_InterEntity> selectConstraintInter(String query);
	public ArrayList<Attribute_InterEntity> findAllInter();
	public boolean updateInter(Attribute_InterEntity inter) throws SQLException;

}
