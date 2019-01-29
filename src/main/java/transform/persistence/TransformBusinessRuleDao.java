package transform.persistence;

import transform.model.*;

public interface TransformBusinessRuleDao {

    boolean transform(BusinessRule rule);

    String GenerateAttributeRange(Attribute_Range range);
    String GenerateAttributeCompare(Attribute_Compare compare);
    String GenerateAttributeList(Attribute_List list);
    String GenerateAttributeOther(Attribute_Other other);
    String GenerateTupleCompare(Tuple_Compare compare);
    String GenerateTupleOther(Tuple_Other other);
    String GenerateInterEntityCompare(InterEntity_Compare compare);
    String GenerateEntityOther(Entity_Other other);

}
