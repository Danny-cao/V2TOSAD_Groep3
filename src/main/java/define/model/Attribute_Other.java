package define.model;

public class Attribute_Other extends Constraint {

    private String attribute;

    public Attribute_Other(String naam, String table, int id, String attribute) {

        super(naam, table, id);
        this.attribute = attribute;

    }

    public String getAttribute() {

        return this.attribute;

    }
}
