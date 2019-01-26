package transform.model;

import transform.persistence.*;

import java.util.ArrayList;

public class TransformService {
    private BusinessRuleDao BDAO = new BusinessRuleOracleDaoImpl();
    private BusinessRuleTypeDao BTDAO = new BusinessRuleTypeOracleDaoImpl();
    private ConstraintDao conDAO = new ConstraintOracleDaoImpl();


    public ArrayList<BusinessRule> getAllBusinessRules(){
        return BDAO.findAll();
    }

    public ArrayList<BusinessRule> findAllBusinessRules() {
        return BDAO.findAll();
    }

    public BusinessRule getBusinessRule(int id){
        return BDAO.findByID(id);
    }

    public BusinessRuleType getBusinessRuleType(int id){
        return BTDAO.findByID(id);
    }

    public Constraint getConstraint(int id) {
        return conDAO.findByID(id);
    }

    TransformBusinessRuleDao tDao = new TransformBusinessRule();

    public boolean transform(BusinessRule rule) {

        return tDao.transform(rule);

    }
}
