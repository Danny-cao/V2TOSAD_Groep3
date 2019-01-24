package define.model;
import java.util.List;

public class Attribute_List extends Constraint {

    private String attribute;
    private List<String> values;

    public Attribute_List(int id, String naam, String table, String attribute, List<String> values) {

        super(naam, table, id);
        this.attribute = attribute;
        this.values = values;

    }

    public String getAttribute() {

        return this.attribute;

    }

    public List<String> getValues() {

        return values;

    }
}
