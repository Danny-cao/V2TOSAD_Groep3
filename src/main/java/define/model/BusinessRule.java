package define.model;

public class BusinessRule {

    private int id;
    private String naam;
    private BusinessRuleType type;
    private Constraint constraint;
    private int businessType;
    private int constraintID;

    public BusinessRule(int id, String naam, BusinessRuleType type, Constraint constraint) {
        this.id = id;
        this.naam = naam;
        this.type = type;
        this.constraint = constraint;
    }

    public BusinessRule(int id, String naam, int businessType,int constraintID ) {
        this.id = id;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;
    }

    public int getId() {
        return id;
    }




    public String getNaam() {
        return naam;
    }

    public BusinessRuleType getType() {
        return type;
    }

    public Constraint getConstraint() {
        return constraint;
    }
}
