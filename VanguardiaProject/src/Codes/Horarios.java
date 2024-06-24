package Codes;

import Utils.Conex;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Horarios {

    public List<Horario> obtenerAulasPorBloqueYSala(String nombreBloque, String tipoEspacio, String nombreSala) {
        List<Horario> horarios = new ArrayList<>();
        Connection con = Conex.getConex();
        if (con != null) {
            String query = "SELECT " +
                    "h.HORA, " +
                    "CASE WHEN h.LUNES IS NULL THEN '' ELSE m.nombre_materia END AS LUNES, " +
                    "CASE WHEN h.MARTES IS NULL THEN '' ELSE m.nombre_materia END AS MARTES, " +
                    "CASE WHEN h.MIERCOLES IS NULL THEN '' ELSE m.nombre_materia END AS MIERCOLES, " +
                    "CASE WHEN h.JUEVES IS NULL THEN '' ELSE m.nombre_materia END AS JUEVES, " +
                    "CASE WHEN h.VIERNES IS NULL THEN '' ELSE m.nombre_materia END AS VIERNES, " +
                    "m.nombre_materia " +
                    "FROM horarios h " +
                    "JOIN espacios e ON h.fk_espacio = e.ID_ESPACIO " +
                    "JOIN bloques b ON e.ID_BLOQUE_PERTENECE = b.ID_BLOQUE " +
                    "JOIN materias m ON h.fk_materia = m.materia_id " +
                    "WHERE b.NOMBRE = ? AND e.TIPO = ? AND e.NOMBRE = ? " +
                    "ORDER BY h.HORA;";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, nombreBloque);
                pstmt.setString(2, tipoEspacio);
                pstmt.setString(3, nombreSala);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String hora = rs.getString("HORA");
                        String lunes = rs.getString("LUNES");
                        String martes = rs.getString("MARTES");
                        String miercoles = rs.getString("MIERCOLES");
                        String jueves = rs.getString("JUEVES");
                        String viernes = rs.getString("VIERNES");
                        String materia = rs.getString("nombre_materia");

                        horarios.add(new Horario(hora, lunes, martes, miercoles, jueves, viernes, materia));
                        System.out.println("Horario obtenido: " + hora + ", " + lunes + ", " + martes + ", " + miercoles + ", " + jueves + ", " + viernes + ", " + materia);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e); e.printStackTrace();
            }
        } else {
            System.out.println("La conexión es nula");
        }
        return horarios;
    }
}
