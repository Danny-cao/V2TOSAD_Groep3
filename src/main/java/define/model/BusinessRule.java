package define.model;

public class BusinessRule {

    private int id;
    private String bid;
    private String naam;
    private BusinessRuleType type;
    private Constraint constraint;
    private Attribute_Compare compare;
    private Attribute_Range range;
    private Attribute_Other other;
    private Attribute_InterEntity inter;
    private int businessType;
    private int constraintID;

    public BusinessRule(int id, String naam, BusinessRuleType type,Constraint constraint) {
        this.id = id;
        this.naam = naam;
        this.type = type;
        this.constraint = constraint;

    }

    public BusinessRule(int id, String naam, BusinessRuleType type, Constraint constraint,
                        Attribute_Compare compare) {
        this.id = id;
        this.naam = naam;
        this.type = type;
        this.constraint = constraint;
        this.compare = compare;

    }

    public BusinessRule(int id, String naam, int businessType,int constraintID,Attribute_Compare compare ) {
        this.id = id;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;
        this.compare = compare;
    }


    public BusinessRule(int id, String naam, int businessType,int constraintID,Attribute_Other other ) {
        this.id = id;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;
        this.other = other;
    }

    public BusinessRule(int id, String naam, int businessType,int constraintID,Attribute_InterEntity inter ) {
        this.id = id;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;
        this.inter = inter;
    }

    public BusinessRule(int id, String naam, int businessType,int constraintID,Attribute_Range range ) {
        this.id = id;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;
        this.range= range;
    }

    public BusinessRule(int id, String naam, int businessType,int constraintID ) {
        this.id = id;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;

    }

    public BusinessRule(String bid, String naam, int businessType,int constraintID ) {
        this.bid = bid;
        this.naam = naam;
        this.businessType = businessType;
        this.constraintID = constraintID;

    }



    public int getId() {
        return id;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public void setCompare(Attribute_Compare compare) {
        this.compare = compare;
    }

    public void setRange(Attribute_Range range) {
        this.range = range;
    }

    public void setOther(Attribute_Other other) {
        this.other = other;
    }

    public void setInter(Attribute_InterEntity inter) {
        this.inter= inter;
    }




    public String getNaam() {
        return naam;
    }

    public int getbusinesstype() {
        return businessType;
    }

    public int getConstraintid() {
        return constraintID;
    }

    public BusinessRuleType getType() {
        return type;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public Attribute_Compare getCompare() {
        return compare;
    }

    public Attribute_Range getRange() {
        return range;
    }

    public Attribute_Other getOther() {
        return other;
    }

    public Attribute_InterEntity getInter() {
        return inter;
    }
}
