package transform.model;

public class BusinessRuleType {
    private int id;
    private String naam;
    private String categorie;

    public BusinessRuleType(int id, String naam, String categorie) {
        this.id = id;
        this.naam = naam;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getCategorie() {
        return categorie;
    }

    public String toString(){
        return getId() + " " + getNaam() + " " + getCategorie();
    }
    
}
