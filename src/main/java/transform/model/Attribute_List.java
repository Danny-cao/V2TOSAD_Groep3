package transform.model;

import java.util.List;

public class Attribute_List extends Constraint {

    private String value;
    private String inList;
    private List<String> values;

    public Attribute_List(int id, String naam, String table, String value, String inList, List<String> values) {

        super(naam, table, id);
        this.value = value;
        this.inList = inList;
        this.values = values;

    }

    public String getValue() {

        return this.value;

    }

    public String getInList() {

        return this.inList;

    }

    public List<String> getValues() {

        return values;

    }

}
