package define.persistence;

import define.model.BusinessRule;
import java.sql.SQLException;

public class BusinessRuleService {
    private BusinessRuleDao BDAO = new BusinessRuleDao();

    public BusinessRuleService() {}

    public BusinessRule Save(BusinessRule rule) throws SQLException{
        return BDAO.save(rule);
    }


}
