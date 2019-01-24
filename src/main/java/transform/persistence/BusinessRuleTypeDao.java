package transform.persistence;

import transform.model.BusinessRuleType;

import java.util.List;

public interface BusinessRuleTypeDao {
    List<BusinessRuleType> findAll();
    BusinessRuleType findByID(int id);
}
