package define.model;

public class Attribute_Compare extends Constraint {

    private String attribute;
    private String value;
    private String operator;

    public Attribute_Compare(String naam, String table, int id, String attribute, String value, String operator) {

        super(naam, table, id);
        this.attribute = attribute;
        this.value = value;
        this.operator = operator;

    }

    public Attribute_Compare(String naam, String table,String attribute, String value, String operator) {

        super(naam,table);
        this.attribute = attribute;
        this.value = value;
        this.operator = operator;

    }




    public String getAttribute() {

        return this.attribute;

    }

    public String getValue() {

        return this.value;

    }

    public String getOperator() {

        return this.operator;

    }

}
