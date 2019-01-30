package define.model;

import define.model.Constraint;

public class Tuple_Compare extends Constraint{

    private String attribute;
    private String ref_attribute;
    private String operator;

    public Tuple_Compare(String naam, String table, int id, String attribute, String ref_attribute, String operator) {
        super(naam, table, id);
        this.attribute = attribute;
        this.ref_attribute = ref_attribute;
        this.operator = operator;
    }

    public Tuple_Compare(String table, String attribute, String ref_attribute, String operator) {
        super(table);
        this.attribute = attribute;
        this.ref_attribute = ref_attribute;
        this.operator = operator;
    }


    public String getAttribute() {
        return attribute;
    }

    public String getRef_attribute() {
        return ref_attribute;
    }

    public String getOperator() {
        return operator;
    }
}
