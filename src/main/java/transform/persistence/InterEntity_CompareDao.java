package transform.persistence;

import transform.model.BusinessRule;
import transform.model.InterEntity_Compare;

public interface InterEntity_CompareDao {
    InterEntity_Compare getInterEntityCompare(BusinessRule rule);
}
