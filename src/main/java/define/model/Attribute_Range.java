package define.model;

public class Attribute_Range extends Constraint{
    private String attribute;
    private double value1;
    private double value2;

    public Attribute_Range(String naam, String table, int id, String attribute, double value1, double value2){

        super(naam, table, id);
        this.attribute = attribute;
        this.value1 = value1;
        this.value2 = value2;

    }

    public String getAttribute() {

        return this.attribute;

    }

    public double getValue1() {

        return this.value1;

    }

    public double getValue2(){

        return this.value2;

    }
}
