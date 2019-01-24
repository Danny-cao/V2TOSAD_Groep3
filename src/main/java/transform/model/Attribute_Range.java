package transform.model;

public class Attribute_Range extends Constraint {

    private String attribute;
    private String between;
    private double value1;
    private double value2;

    public Attribute_Range(String naam, String table, int id, String attribute, String between, double value1, double value2){

        super(naam, table, id);
        this.attribute = attribute;
        this.between = between;
        this.value1 = value1;
        this.value2 = value2;

    }

    public String getAttribute() {

        return this.attribute;

    }

    public String getBetween() {

        return this.between;

    }

    public double getValue1() {

        return this.value1;

    }

    public double getValue2(){

        return this.value2;

    }

}
