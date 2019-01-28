package define.model;

import define.persistence.*;

public class DefineService {
    private BusinessRuleDao BDAO = new BusinessRuleOracleDaoImpl();


    public BusinessRule getBusinessRuleByID(int id){
        return BDAO.getBusinessRuleById(id);
    }

    public BusinessRule getBusinessRule(String naam, String table, int type){
        return BDAO.getBusinessRule(naam, table, type);
    }
}
