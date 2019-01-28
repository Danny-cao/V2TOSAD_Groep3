package define.model;

import define.persistence.*;

import java.util.List;

public class DefineService {
    private BusinessRuleDao bdao = new BusinessRuleOracleDaoImpl();


    public BusinessRule getBusinessRuleByID(int id){
        return bdao.getBusinessRuleById(id);
    }

    public BusinessRule getBusinessRule(String naam, String table, int type){
        return bdao.getBusinessRule(naam, table, type);
    }

    public List<BusinessRule> getAllBusinessRules(){
        return bdao.getAllBusinessRules();
    }

    public boolean delete(BusinessRule rule){
        return bdao.delete(rule);
    }

    public BusinessRule update(BusinessRule rule){
        return bdao.update(rule);
    }

}
