package transform.persistence;

import transform.model.BusinessRule;

import java.util.List;

public interface BusinessRuleDao {

    List<BusinessRule> findAll();

    BusinessRule findByID(int id);

    boolean save(BusinessRule businessRule);

    boolean update(BusinessRule businessRule);

    boolean delete(BusinessRule businessRule);

}