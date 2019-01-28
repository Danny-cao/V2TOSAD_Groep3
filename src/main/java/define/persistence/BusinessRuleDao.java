package define.persistence;

import define.model.BusinessRule;

public interface BusinessRuleDao {
    BusinessRule getBusinessRuleById(int id);
    BusinessRule getBusinessRule(String naam, String table, int type);
}

