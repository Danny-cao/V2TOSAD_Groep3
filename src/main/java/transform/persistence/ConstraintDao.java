package transform.persistence;

import transform.model.Constraint;

public interface ConstraintDao {

    Constraint findAll();
    Constraint findByID(int id);

}
