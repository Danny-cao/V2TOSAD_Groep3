package transform.persistence;

import transform.model.Attribute_Range;
import transform.model.BusinessRule;
import transform.model.Constraint;

public interface TransformBusinessRuleDao {

    public boolean transform(BusinessRule rule);

    public String GenerateAttributeRange(Attribute_Range range);
}
