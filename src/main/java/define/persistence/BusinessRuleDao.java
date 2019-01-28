package define.persistence;

import define.model.BusinessRule;

import java.util.List;

public interface BusinessRuleDao {
    BusinessRule getBusinessRuleById(int id);
    BusinessRule getBusinessRule(String naam, String table, int type);
    List<BusinessRule> getAllBusinessRules();
    BusinessRule update(BusinessRule rule);
    boolean delete(BusinessRule rule);
}

