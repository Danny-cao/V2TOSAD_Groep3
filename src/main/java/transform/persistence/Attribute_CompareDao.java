package transform.persistence;

import transform.model.Attribute_Compare;
import transform.model.BusinessRule;

public interface Attribute_CompareDao {

    Attribute_Compare findByID(int id);
    Attribute_Compare getAttribute_Compare(BusinessRule rule);

}
