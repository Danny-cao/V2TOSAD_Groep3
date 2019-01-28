package define.model;

public class Constraint {

    private int id;
    private String naam;
    private String table;

    public Constraint(String naam, String table, int id) {

        this.id = id;
        this.naam = naam;
        this.table = table;

    }

    public Constraint(String naam, String table) {

        this.naam = naam;
        this.table = table;

    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getTable() {
        return table;
    }
}
