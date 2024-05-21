package Codes;

import Utils.Conex;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bloques {

    public List<String> BloquesUni() {
        List<String> bloques = new ArrayList<>();
        Connection con = Conex.getConex();
        if (con != null) {
            String query = "SELECT NOMBRE FROM bloques"; 
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    bloques.add(rs.getString("NOMBRE"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bloques;
    }
}

