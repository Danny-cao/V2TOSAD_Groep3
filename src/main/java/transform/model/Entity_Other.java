package transform.model;

public class Entity_Other extends Constraint {

    private String constraint;

    public Entity_Other(String naam, String table, int id, String constraint){

        super(naam, table, id);
        this.constraint = constraint;

    }

    public String getConstraint(){

        return this.constraint;

    }

}
