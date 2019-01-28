import transform.model.Attribute_Range;
import transform.model.BusinessRule;
import transform.model.BusinessRuleType;
import transform.persistence.BusinessRuleDao;
import transform.persistence.BusinessRuleOracleDaoImpl;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hallo wereld!");

        BusinessRuleType brt1 = new BusinessRuleType(1, "Type1", "Categorie1");
        Attribute_Range c1 = new Attribute_Range("Test", "Table", 1, "Attribute", "between",1, 10);
        BusinessRule br1 = new BusinessRule(1, "Test", brt1, c1);

        BusinessRuleDao dao = new BusinessRuleOracleDaoImpl();

        System.out.println(((Attribute_Range) br1.getConstraint()).getOperator());

    } //tekst
}
