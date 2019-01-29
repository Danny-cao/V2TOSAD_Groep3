package define.model;

public class Attribute_Other extends Constraint {

    private String attribute1;
    private String attribute2;
    private String operator;


    public Attribute_Other(String naam, String table, int id, String attribute1,String attribute2,
                           String operator) {

        super(naam, table, id);
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.operator = operator;

    }

    public Attribute_Other(String table, int id, String attribute1,String attribute2,
                           String operator) {

        super(table, id);
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.operator = operator;

    }

    public String getAttribute1() {

        return this.attribute1;

    }

    public String getAttribute2() {

        return this.attribute2;

    }

    public String getOperator() {

        return this.operator;

    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public void setOperator(String operator){
        this.operator = operator;
    }
}
