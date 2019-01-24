package transform.persistence;

import transform.model.BusinessRule;

public interface TransformBusinessRuleDao {

    public boolean transform(BusinessRule rule);

}
