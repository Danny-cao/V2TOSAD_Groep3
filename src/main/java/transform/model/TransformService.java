package transform.model;

import transform.persistence.TransformBusinessRule;
import transform.persistence.TransformBusinessRuleDao;

public class TransformService {

    TransformBusinessRuleDao tDao = new TransformBusinessRule();

    public boolean transform(BusinessRule rule) {

        return tDao.transform(rule);

    }

}
