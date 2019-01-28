package transform.model;

import transform.persistence.BusinessRuleDao;
import transform.persistence.BusinessRuleOracleDaoImpl;

public class BusinessRule {

    private int id;
    private String naam;
    private BusinessRuleType type;
    private Constraint constraint;

    public BusinessRule(int id, String naam, BusinessRuleType type, Constraint constraint) {
        this.id = id;
        this.naam = naam;
        this.type = type;
        this.constraint = constraint;
    }

    public BusinessRule(int id, String naam){
        this.id = id;
        this.naam = naam;
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

    public String toString(){
        return getId() + " " + getNaam() + " " + getType().toString() + " " + getConstraint().toString();
    }
    }