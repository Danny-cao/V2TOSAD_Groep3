package define.persistence;


import define.model.Constraint;

import java.sql.SQLException;
import java.util.List;

public interface ConstraintDao{
    Constraint getConstraintByID(int id);
    boolean delete(Constraint constraint);

    Constraint findByidRange(int id) throws SQLException;
    Constraint findByidOther(int id) throws SQLException;
    Constraint findByidInter(int id) throws SQLException;
    List<Constraint> selectConstraint(String query);
    List<Constraint> findAll();
    Constraint findByConstraintnummer(int constraintnummer);
    int createUniqueID();
}
