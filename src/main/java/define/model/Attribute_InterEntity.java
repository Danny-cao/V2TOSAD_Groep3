package define.model;

public class Attribute_InterEntity extends Constraint {
	
	private String ref_tabel;
	private String attribute1;
    private String attribute2;
    private String operator;
   

    public Attribute_InterEntity(String naam, String table, int id, String attribute1,String attribute2,
    		String operator, String ref_table) {

        super(naam, table, id);
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.operator = operator;
        this.ref_tabel = ref_table;

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
    
    public String getRef_table() {

        return this.ref_tabel;

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
    
    public void setRef_tabel(String ref_tabel){
        this.ref_tabel = ref_tabel;
    }
	

}
