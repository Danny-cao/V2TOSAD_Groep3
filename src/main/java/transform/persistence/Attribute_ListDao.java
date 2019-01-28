package transform.persistence;

import transform.model.BusinessRule;
import transform.model.Attribute_List;

public interface Attribute_ListDao {

    public Attribute_List getAttribute_List(BusinessRule rule);

}
