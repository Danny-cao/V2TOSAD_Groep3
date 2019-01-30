package define.persistence;


import persistence.OracleBaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabledefOracleDaoImpl extends OracleBaseDao implements TabledefDao{

    private Connection conn;

    public TabledefOracleDaoImpl() {
        try {
            conn = super.getConnection();
        } catch (SQLException e) {
            System.out.println("Error: could not connect to database.");
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getTabledef() {

        List<String> allTables = new ArrayList<>();

        try {

            String queryText =  "SELECT table_name FROM dba_tables WHERE table_name LIKE 'VBMG%' ";
            PreparedStatement stmt = conn.prepareStatement(queryText);

            ResultSet result = stmt.executeQuery();



            while(result.next()){
                String name = result.getString("table_name");

                allTables.add(name);
            }

            return allTables;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
