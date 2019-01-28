package transform.model;

public class InterEntity_Compare extends Constraint{

    private String attribute;
    private String ref_table;
    private String ref_attribute;
    private String operator;


    public InterEntity_Compare(String naam, String table, int id, String attribute, String ref_table, String ref_attribute, String operator) {
        super(naam, table, id);
        this.attribute = attribute;
        this.ref_table = ref_table;
        this.ref_attribute = ref_attribute;
        this.operator = operator;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getRef_table() {
        return ref_table;
    }

    public String getRef_attribute() {
        return ref_attribute;
    }

    public String getOperator() {
        return operator;
    }
}
