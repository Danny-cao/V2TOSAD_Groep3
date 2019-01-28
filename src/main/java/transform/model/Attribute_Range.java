package transform.model;

public class Attribute_Range extends Constraint {

    private String attribute;
    private String operator;
    private double value1;
    private double value2;

    public Attribute_Range(String naam, String table, int id, String attribute, String operator, double value1, double value2){

        super(naam, table, id);
        this.attribute = attribute;
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;

    }

    public String getAttribute() {

        return this.attribute;

    }

    public String getOperator() {

        return this.operator;

    }

    public double getValue1() {

        return this.value1;

    }

    public double getValue2(){

        return this.value2;

    }

}
