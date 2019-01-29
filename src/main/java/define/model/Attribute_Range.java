package define.model;

public class Attribute_Range extends Constraint{

    private String attribute;
    private int value1;
    private int value2;
    private String operator;

    public Attribute_Range(String naam, String table, int id, String attribute, int value1, int value2, String operator){

        super(naam, table, id);
        this.attribute = attribute;
        this.value1 = value1;
        this.value2 = value2;
        this.operator = operator;

    }

    public String getAttribute() {

        return this.attribute;

    }

    public String getOperator() {

        return this.operator;

    }

    public int getValue1() {

        return this.value1;

    }

    public int getValue2(){

        return this.value2;

    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public void setOperator(String operator){
        this.operator = operator;
    }
}
