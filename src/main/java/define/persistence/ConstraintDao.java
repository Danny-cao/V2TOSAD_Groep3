package define.persistence;


import define.model.Constraint;

public interface ConstraintDao{
    Constraint getConstraintByID(int id);
    boolean delete(Constraint constraint);
}
