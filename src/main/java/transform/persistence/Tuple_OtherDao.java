package transform.persistence;

import transform.model.BusinessRule;
import transform.model.Tuple_Other;

public interface Tuple_OtherDao {
    Tuple_Other getTuple_Other(BusinessRule rule);
}
