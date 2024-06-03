package Codes;

import Utils.Conex;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Lab_Aulas {
    public List<String> obtenerAulasPorBloque(String nombreBloque, String tipoEspacio) {
        List<String> aulas = new ArrayList<>();
        Connection con = Conex.getConex();
        if (con != null) {
            String query = "SELECT NOMBRE FROM espacios WHERE ID_BLOQUE_PERTENECE = " +
                    "(SELECT ID_BLOQUE FROM bloques WHERE NOMBRE = ?) AND TIPO = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, nombreBloque);
                pstmt.setString(2, tipoEspacio);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        aulas.add(rs.getString("NOMBRE"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aulas;
    }
}

