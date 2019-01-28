package define.persistence;


import define.model.BusinessRuleType;
import define.model.BusinessRule;

public interface BusinessRuleDao {
    BusinessRule getBusinessRuleById(int id);
    BusinessRule getBusinessRule(String naam, String table, BusinessRuleType type);
}

