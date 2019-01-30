package transform.persistence;

import transform.model.BusinessRule;
import transform.model.Entity_Other;

public interface Entity_OtherDao {

    Entity_Other findByID(int id);
    Entity_Other getEntity_Other(BusinessRule rule);

}
