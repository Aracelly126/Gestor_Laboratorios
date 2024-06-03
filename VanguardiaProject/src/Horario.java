import Utils.Conex;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Horario {
    // Method to obtain schedule for a specific classroom or laboratory
    public List<String> obtenerHorarioAula(String nombreAula) {
        List<String> horario = new ArrayList<>();
        Connection con = Conex.getConex();
        if (con != null) {
            String query = "SELECT DISTINCT materia.nombre_materia, horarios.dia, horarios.hora_inicio, horarios.hora_fin " +
                           "FROM horarios " +
                           "JOIN espacios ON horarios.fk_espacio = espacios.ID_ESPACIO " +
                           "JOIN materias ON horarios.fk_materia = materias.materia_id " +
                           "WHERE espacios.NOMBRE = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, nombreAula);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String materia = rs.getString("nombre_materia");
                        String dia = rs.getString("dia");
                        Time horaInicio = rs.getTime("hora_inicio");
                        Time horaFin = rs.getTime("hora_fin");
                        String horarioStr = materia + " - " + dia + " - " + horaInicio + " - " + horaFin;
                        horario.add(horarioStr);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return horario;
    }
}



