package Codes;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.Conex;
import java.util.ArrayList;
import java.util.List;

public class bd_materias {

   public boolean crearMateria(String nombre, int semestreId, String nombreCarrera) {
    String sqlCheckCarreraSemestre = "SELECT c.carrera_id " +
                                     "FROM carreras c " +
                                     "JOIN semestres s ON c.carrera_id = s.carrera_id " +
                                     "WHERE s.semestre_id = ? AND c.nombre_carrera = ?";
    String sqlCheckMateria = "SELECT * FROM materias WHERE nombre_materia = ? AND semestre_id = ?";
    String sqlInsert = "INSERT INTO materias (nombre_materia, semestre_id) VALUES (?, ?)";
    PreparedStatement pstmtCheckCarreraSemestre = null;
    PreparedStatement pstmtCheckMateria = null;
    PreparedStatement pstmtInsert = null;
    ResultSet rs = null;

    try {
        Connection conn = Conex.getConex();
        if (conn == null) {
            throw new SQLException("Conexión a la base de datos fallida");
        }

        // Verificar si la carrera es válida para el semestre dado
        pstmtCheckCarreraSemestre = conn.prepareStatement(sqlCheckCarreraSemestre);
        pstmtCheckCarreraSemestre.setInt(1, semestreId);
        pstmtCheckCarreraSemestre.setString(2, nombreCarrera);
        rs = pstmtCheckCarreraSemestre.executeQuery();

        if (rs.next()) {
            int carreraId = rs.getInt("carrera_id");

            // Verificar si la materia ya existe en el semestre dado
            pstmtCheckMateria = conn.prepareStatement(sqlCheckMateria);
            pstmtCheckMateria.setString(1, nombre);
            pstmtCheckMateria.setInt(2, semestreId);
            ResultSet rsMateria = pstmtCheckMateria.executeQuery();

            if (rsMateria.next()) {
                JOptionPane.showMessageDialog(null, "La materia ya existe en la base de datos para este semestre.");
                return false;
            } else {
                // Insertar la nueva materia
                pstmtInsert = conn.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, nombre);
                pstmtInsert.setInt(2, semestreId);
                int rowsInserted = pstmtInsert.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Materia agregada con éxito.");
                    return true;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La carrera especificada no es válida para el semestre dado.");
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar la materia: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmtCheckCarreraSemestre != null) {
                pstmtCheckCarreraSemestre.close();
            }
            if (pstmtCheckMateria != null) {
                pstmtCheckMateria.close();
            }
            if (pstmtInsert != null) {
                pstmtInsert.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    return false;
}


    public List<Materia> obtenerMaterias() {
    String sql = "SELECT m.materia_id AS id, m.nombre_materia AS nombre_materia, s.semestre_id AS semestre_id, s.numero_semestre AS numero_semestre, c.carrera_id AS carrera_id, c.nombre_carrera AS nombre_carrera " +
                 "FROM materias m " +
                 "JOIN semestres s ON m.semestre_id = s.semestre_id " +
                 "JOIN carreras c ON s.carrera_id = c.carrera_id";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Materia> materias = new ArrayList<>();

    try {
        Connection conn = Conex.getConex();
        if (conn == null) {
            throw new SQLException("Conexión a la base de datos fallida");
        }
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre_materia");
            int semestreId = rs.getInt("semestre_id");
            int numeroSemestre = rs.getInt("numero_semestre");
            int carreraId = rs.getInt("carrera_id");
            String nombreCarrera = rs.getString("nombre_carrera");
            materias.add(new Materia(id, nombre, semestreId, numeroSemestre, carreraId, nombreCarrera));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener materias: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    return materias;
}
   public boolean editarMateria(int idMateria, String nombre, int semestreId, String nombreCarrera) {
    String sqlCheckCarrera = "SELECT carrera_id FROM carreras WHERE nombre_carrera = ?";
    String sqlCheckMateria = "SELECT * FROM materias WHERE nombre_materia = ? AND semestre_id = ? AND carrera_id = ? AND materia_id != ?";
    String sqlUpdate = "UPDATE materias SET nombre_materia = ?, semestre_id = ?, carrera_id = ? WHERE materia_id = ?";
    PreparedStatement pstmtCheckCarrera = null;
    PreparedStatement pstmtCheckMateria = null;
    PreparedStatement pstmtUpdate = null;
    ResultSet rs = null;

    try {
        Connection conn = Conex.getConex();
        if (conn == null) {
            throw new SQLException("Conexión a la base de datos fallida");
        }

        // Verificar si la carrera existe y obtener el carrera_id
        pstmtCheckCarrera = conn.prepareStatement(sqlCheckCarrera);
        pstmtCheckCarrera.setString(1, nombreCarrera);
        rs = pstmtCheckCarrera.executeQuery();
        
        if (rs.next()) {
            int carreraId = rs.getInt("carrera_id");

            // Verificar si la materia ya existe con los nuevos datos
            pstmtCheckMateria = conn.prepareStatement(sqlCheckMateria);
            pstmtCheckMateria.setString(1, nombre);
            pstmtCheckMateria.setInt(2, semestreId);
            pstmtCheckMateria.setInt(3, carreraId);
            pstmtCheckMateria.setInt(4, idMateria);
            ResultSet rsMateria = pstmtCheckMateria.executeQuery();

            if (rsMateria.next()) {
                JOptionPane.showMessageDialog(null, "Una materia con estos datos ya existe.");
                return false;
            } else {
                // Actualizar la materia
                pstmtUpdate = conn.prepareStatement(sqlUpdate);
                pstmtUpdate.setString(1, nombre);
                pstmtUpdate.setInt(2, semestreId);
                pstmtUpdate.setInt(3, carreraId);
                pstmtUpdate.setInt(4, idMateria);
                int rowsUpdated = pstmtUpdate.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Materia actualizada con éxito.");
                    return true;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La carrera especificada no existe.");
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar la materia: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmtCheckCarrera != null) {
                pstmtCheckCarrera.close();
            }
            if (pstmtCheckMateria != null) {
                pstmtCheckMateria.close();
            }
            if (pstmtUpdate != null) {
                pstmtUpdate.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    return false;
}


    public boolean eliminarMateria(int idMateria) {
        String sql = "DELETE FROM materias WHERE materia_id = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = Conex.getConex();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, idMateria);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Materia eliminada con éxito.");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la materia: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
            }
        }
        return false;
    }
}
