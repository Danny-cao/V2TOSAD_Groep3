package define.model;
import define.persistence.ConstraintService;
import define.persistence.BusinessRuleService;


public class ServiceProvider {

    private static BusinessRuleService businessRuleService = new BusinessRuleService();
    //private static BusinessTypeService businessTypeService = new BusinessTypeService();
    private static ConstraintService constraintService = new ConstraintService();

    public static BusinessRuleService getBusinessRuleService() {
      return businessRuleService ;
    }

    //public static BusinessTypeService getBusinessTypeService() {
      //  return businessTypeService;
    //}


    public static ConstraintService getConstraintService() {
        return constraintService;
    }



}

