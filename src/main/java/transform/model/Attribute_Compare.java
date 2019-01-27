package transform.model;

public class Attribute_Compare extends Constraint {

    private String attribute;
    private double value;
    private String operator;

    public Attribute_Compare(String naam, String table, int id, String attribute, double value, String operator) {

        super(naam, table, id);
        this.attribute = attribute;
        this.value = value;
        this.operator = operator;

    }

    public String getAttribute() {

        return this.attribute;

    }

    public double getValue() {

        return this.value;

    }

    public String getOperator() {

        return this.operator;

    }

}
