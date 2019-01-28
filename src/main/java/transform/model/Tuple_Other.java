package transform.model;

public class Tuple_Other extends Constraint {

    private String attribute;
    private String ref_attribute;
    private String operator;

    public Tuple_Other(String naam, String table, int id, String attribute, String ref_attribute, String operator) {

        super(naam, table, id);
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
