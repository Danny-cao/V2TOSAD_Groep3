package transform.persistence;

import transform.model.*;

public interface TransformBusinessRuleDao {

    boolean transform(BusinessRule rule);

    String GenerateAttributeRange(Attribute_Range range);
    String GenerateAttributeCompare(Attribute_Compare compare);
    String GenerateTupleCompare(Tuple_Compare compare);
}
