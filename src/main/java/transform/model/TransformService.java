package transform.model;

import transform.persistence.TransformBusinessRule;
import transform.persistence.TransformBusinessRuleDao;

import transform.persistence.BusinessRuleDao;
import transform.persistence.BusinessRuleOracleDaoImpl;

import java.util.ArrayList;

public class TransformService {
    private BusinessRuleDao BDAO = new BusinessRuleOracleDaoImpl();


    public ArrayList<BusinessRule> getAllBusinessRules(){
        return BDAO.findAll();
    }

    public ArrayList<BusinessRule> findAllBusinessRules() {
        return BDAO.findAll();
    }

    public BusinessRule getBusinessRule(int id){
        return BDAO.findByID(id);
    }

    TransformBusinessRuleDao tDao = new TransformBusinessRule();

    public boolean transform(BusinessRule rule) {

        return tDao.transform(rule);

    }
}
