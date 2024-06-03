package Codes;

import Utils.Conex;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HorariosManager {
    public List<Horario> obtenerHorariosPorAula(String nombreAula) {
        List<Horario> horarios = new ArrayList<>();
        Connection con = Conex.getConex();
        if (con != null) {
            String query = "SELECT m.nombre_materia, h.hora_inicio, h.hora_fin, h.dia " +
                           "FROM horarios h " +
                           "INNER JOIN materias m ON h.fk_materia = m.materia_id " +
                           "INNER JOIN espacios e ON h.fk_espacio = e.ID_ESPACIO " +
                           "WHERE e.NOMBRE = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, nombreAula);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String nombreMateria = rs.getString("nombre_materia");
                        Time horaInicio = rs.getTime("hora_inicio");
                        Time horaFin = rs.getTime("hora_fin");
                        String dia = rs.getString("dia");
                        horarios.add(new Horario(nombreMateria, horaInicio, horaFin, dia));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return horarios;
    }

    public static class Horario {
        private String nombreMateria;
        private Time horaInicio;
        private Time horaFin;
        private String dia;

        public Horario(String nombreMateria, Time horaInicio, Time horaFin, String dia) {
            this.nombreMateria = nombreMateria;
            this.horaInicio = horaInicio;
            this.horaFin = horaFin;
            this.dia = dia;
        }

        // Getters y setters
        public String getNombreMateria() {
            return nombreMateria;
        }

        public void setNombreMateria(String nombreMateria) {
            this.nombreMateria = nombreMateria;
        }

        public Time getHoraInicio() {
            return horaInicio;
        }

        public void setHoraInicio(Time horaInicio) {
            this.horaInicio = horaInicio;
        }

        public Time getHoraFin() {
            return horaFin;
        }

        public void setHoraFin(Time horaFin) {
            this.horaFin = horaFin;
        }

        public String getDia() {
            return dia;
        }

        public void setDia(String dia) {
            this.dia = dia;
        }
    }
}



