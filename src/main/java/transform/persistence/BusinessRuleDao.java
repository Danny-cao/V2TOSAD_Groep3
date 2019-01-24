package transform.persistence;

import java.sql.SQLException;
import transform.model.BusinessRule;
import java.util.ArrayList;

public interface BusinessRuleDao {

    ArrayList<BusinessRule> selectBusinessRules(String query);
    ArrayList<BusinessRule> findAll();
    public BusinessRule executeQuery(String query);

    BusinessRule findByID(int id);

    boolean save(BusinessRule businessRule);

    boolean update(BusinessRule businessRule);

    boolean delete(BusinessRule businessRule);

}