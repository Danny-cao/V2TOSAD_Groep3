package transform.persistence;

import transform.model.Attribute_Range;
import transform.model.BusinessRule;
import transform.model.Constraint;

public interface TransformBusinessRuleDao {

    boolean transform(BusinessRule rule);

    String GenerateAttributeRange(Attribute_Range range);
}
