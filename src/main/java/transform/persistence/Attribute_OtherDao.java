package transform.persistence;

import transform.model.Attribute_Other;
import transform.model.BusinessRule;

public interface Attribute_OtherDao {

    Attribute_Other findByID(int id);
    public Attribute_Other getAttribute_Other(BusinessRule rule);

}
