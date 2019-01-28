package define.persistence;

import define.model.BusinessRuleType;

public interface BusinessRuleTypeDao {
    BusinessRuleType getBusinessRuleTypeByID(int id);
}
