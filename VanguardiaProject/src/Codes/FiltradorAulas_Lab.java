package Codes;

import Utils.Conex;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FiltradorAulas_Lab {

    public static List<String> obtenerTiposPorBloque(String nombreBloque) {
        List<String> tipos = new ArrayList<>();
        Connection con = Conex.getConex();
        if (con != null) {
            String query = "SELECT DISTINCT tipo FROM espacios WHERE id_bloque_pertenece = "
                    + "(SELECT id_bloque FROM bloques WHERE NOMBRE = ?)";

            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, nombreBloque);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    tipos.add(rs.getString("tipo"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tipos;
    }
}

