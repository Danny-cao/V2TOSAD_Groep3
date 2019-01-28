package transform.persistence;

import transform.model.BusinessRule;
import transform.model.Entity_Other;

public interface Entity_OtherDao {
    Entity_Other getEntityOther(BusinessRule rule);
}
