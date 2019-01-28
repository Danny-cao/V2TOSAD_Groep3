package transform.persistence;

import transform.model.BusinessRule;
import transform.model.Tuple_Compare;

public interface Tuple_CompareDao {
    Tuple_Compare getTuple_Compare(BusinessRule rule);

}
