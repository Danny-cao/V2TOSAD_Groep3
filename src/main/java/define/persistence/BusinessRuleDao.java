package define.persistence;

import define.model.BusinessRule;
import define.model.BusinessRuleType;
import define.model.Constraint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//
public interface BusinessRuleDao {
    BusinessRule getBusinessRuleById(int id);
    BusinessRule getBusinessRule(String naam, String table, int type);
    List<BusinessRule> getAllBusinessRules();
    List<BusinessRule> findAllCompare();
    List<BusinessRule> findAllRange();
    List<BusinessRule> findAllOther();
    List<BusinessRule> findAllInter();

    BusinessRule update(BusinessRule rule);
    boolean delete(BusinessRule rule);
    int createUniqueID();
}

