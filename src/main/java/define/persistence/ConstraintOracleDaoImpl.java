package define.persistence;

import define.model.*;
import persistence.OracleBaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConstraintOracleDaoImpl extends OracleBaseDao implements ConstraintDao {

    private Connection conn;

    public ConstraintOracleDaoImpl() {
        try {
            conn = super.getConnection();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public Constraint getConstraintByID(int id) {

        try {
            String queryText =  "SELECT ID, NAAM, TABLE_NAME " +
                    "FROM CONSTRAINT " +
                    "WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryText);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.next();

            String naam = result.getString("NAAM");
            String table = result.getString("TABLE_NAME");

            return new Constraint(naam, table, id);
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Constraint constraint) {

        boolean success = false;

        try{

            String queryText = "DELETE " +
                    "FROM CONSTRAINT " +
                    "WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, constraint.getId());

            if(stmt.executeUpdate() > 0) {
                success = true;
            }

            return success;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Constraint findByidCompare(int id) throws SQLException {

        Attribute_Compare constraint = null;
        Connection c = super.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM constraint WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            constraint = new Attribute_Compare(
                    rs.getString("naam"),
                    rs.getString("table_name"),
                    rs.getInt("id"),
                    rs.getString("ref_attribute"),
                    rs.getString("value"),
                    rs.getString("operator")


            );

        }
        return constraint;
    }

    @Override
    public Constraint findByidRange(int id) throws SQLException {

        Attribute_Range constraint = null;
        Connection c = super.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM constraint WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            constraint = new Attribute_Range(
                    rs.getString("naam"),
                    rs.getString("table_name"),
                    rs.getInt("id"),
                    rs.getString("ref_attribute"),
                    rs.getInt("value"),
                    rs.getInt("value2"),
                    rs.getString("operator")


            );

        }
        return constraint;
    }

    @Override
    public Constraint findByidOther(int id) throws SQLException{

        Attribute_Other constraint = null;
        Connection c = super.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM constraint WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            constraint = new Attribute_Other(
                    rs.getString("naam"),
                    rs.getString("table_name"),
                    rs.getInt("id"),
                    rs.getString("ref_attribute"),
                    rs.getString("operator"),
                    rs.getString("attribute_name")


            );

        }
        return constraint;

    }

    @Override
    public Constraint findByidInter(int id) throws SQLException{

        Attribute_InterEntity constraint = null;
        Connection c = super.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM constraint WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            constraint = new Attribute_InterEntity(
                    rs.getString("naam"),
                    rs.getString("table_name"),
                    rs.getInt("id"),
                    rs.getString("ref_table"),
                    rs.getString("ref_attribute"),
                    rs.getString("operator"),
                    rs.getString("attribute_name")



            );

        }
        return constraint;
    }

    @Override
    public List<Constraint> selectConstraint(String query) {

        ArrayList<Constraint> results = new ArrayList<Constraint>();
        try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery(query);

            while (dbResultSet.next()) {
                int constraintnummer = dbResultSet.getInt("id");
                String naam = dbResultSet.getString("naam");
                String table = dbResultSet.getString("table_name");
                String atribuut = dbResultSet.getString("ref_attribute");
                String operator = dbResultSet.getString("operator");
                String value = dbResultSet.getString("value");


                Constraint beperking = new Attribute_Compare(table,naam,constraintnummer,atribuut,operator,value);


                results.add(beperking);
            }
        } catch (SQLException sqle) { sqle.printStackTrace(); }

        return results;
    }

    @Override
    public List<Constraint> findAll() {
        return selectConstraint("SELECT * from constraint");
    }

    public Constraint findByConstraintnummer(int constraintnummer) {
        return selectConstraint("SELECT * from constraint where id = " + constraintnummer).get(0);
    }

    @Override
    public int createUniqueID() {
        try {
            String queryText =  "SELECT MAX(ID) + 1 as newID " +
                    "FROM CONSTRAINT";

            PreparedStatement stmt = conn.prepareStatement(queryText);

            ResultSet result = stmt.executeQuery();

            result.next();

            int id = result.getInt("newID");
            System.out.println(id);
            return id;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
