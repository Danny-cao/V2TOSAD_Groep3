package transform.persistence;

import transform.model.Attribute_Range;
import transform.model.BusinessRule;

public interface Attribute_RangeDao {

    Attribute_Range findByID(int id);
    Attribute_Range getAttribute_Range(BusinessRule rule);

}
