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

   public boolean crearMateria(String nombre, int numeroSemestre, String nombreCarrera) {
    String sqlCheckCarrera = "SELECT carrera_id FROM carreras WHERE nombre_carrera = ?";
    String sqlCheckSemestre = "SELECT semestre_id FROM semestres WHERE numero_semestre = ? AND carrera_id = ?";
    String sqlCheckMateria = "SELECT * FROM materias WHERE nombre_materia = ? AND semestre_id = ? AND carrera_id = ?";
    String sqlInsert = "INSERT INTO materias (nombre_materia, semestre_id, carrera_id) VALUES (?, ?, ?)";
    PreparedStatement pstmtCheckCarrera = null;
    PreparedStatement pstmtCheckSemestre = null;
    PreparedStatement pstmtCheckMateria = null;
    PreparedStatement pstmtInsert = null;
    ResultSet rs = null;

    try {
        Connection conn = Conex.getConex();
        if (conn == null) {
            throw new SQLException("Conexión a la base de datos fallida");
        }

        // Verificar si la carrera ingresada existe y obtener carrera_id
        pstmtCheckCarrera = conn.prepareStatement(sqlCheckCarrera);
        pstmtCheckCarrera.setString(1, nombreCarrera);
        rs = pstmtCheckCarrera.executeQuery();

        if (rs.next()) {
            int carreraId = rs.getInt("carrera_id");
            System.out.println("Carrera ID validada: " + carreraId); // Depuración

            // Verificar si el semestre ingresado existe para la carrera y obtener semestre_id
            pstmtCheckSemestre = conn.prepareStatement(sqlCheckSemestre);
            pstmtCheckSemestre.setInt(1, numeroSemestre);
            pstmtCheckSemestre.setInt(2, carreraId);
            ResultSet rsSemestre = pstmtCheckSemestre.executeQuery();

            if (rsSemestre.next()) {
                int semestreId = rsSemestre.getInt("semestre_id");
                System.out.println("Semestre ID validado: " + semestreId); // Depuración

                // Verificar si la materia ya existe para la combinación de semestre y carrera
                pstmtCheckMateria = conn.prepareStatement(sqlCheckMateria);
                pstmtCheckMateria.setString(1, nombre);
                pstmtCheckMateria.setInt(2, semestreId);
                pstmtCheckMateria.setInt(3, carreraId);
                ResultSet rsMateria = pstmtCheckMateria.executeQuery();

                if (rsMateria.next()) {
                    JOptionPane.showMessageDialog(null, "La materia ya existe en la base de datos para esta combinación de semestre y carrera.");
                    return false;
                } else {
                    // Insertar la nueva materia con semestre_id y carrera_id
                    pstmtInsert = conn.prepareStatement(sqlInsert);
                    pstmtInsert.setString(1, nombre);
                    pstmtInsert.setInt(2, semestreId);
                    pstmtInsert.setInt(3, carreraId);
                    System.out.println("Insertando materia: " + nombre + ", Semestre ID: " + semestreId + ", Carrera ID: " + carreraId); // Depuración
                    int rowsInserted = pstmtInsert.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Materia agregada con éxito.");
                        return true;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El número de semestre especificado no es válido para la carrera dada.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "La carrera especificada no es válida.");
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar la materia: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmtCheckCarrera != null) {
                pstmtCheckCarrera.close();
            }
            if (pstmtCheckSemestre != null) {
                pstmtCheckSemestre.close();
            }
            if (pstmtCheckMateria != null) {
                pstmtCheckMateria.close();
            }
            if (pstmtInsert != null) {
                pstmtInsert.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
            e.printStackTrace();
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
  public boolean editarMateria(int idMateria, String nombre, int numeroSemestre, String nombreCarrera) {
    String sqlCheckCarrera = "SELECT carrera_id FROM carreras WHERE nombre_carrera = ?";
    String sqlCheckSemestre = "SELECT semestre_id FROM semestres WHERE numero_semestre = ? AND carrera_id = ?";
    String sqlCheckMateria = "SELECT * FROM materias WHERE nombre_materia = ? AND semestre_id = ? AND carrera_id = ? AND materia_id != ?";
    String sqlUpdate = "UPDATE materias SET nombre_materia = ?, semestre_id = ?, carrera_id = ? WHERE materia_id = ?";
    PreparedStatement pstmtCheckCarrera = null;
    PreparedStatement pstmtCheckSemestre = null;
    PreparedStatement pstmtCheckMateria = null;
    PreparedStatement pstmtUpdate = null;
    ResultSet rs = null;

    try {
        Connection conn = Conex.getConex();
        if (conn == null) {
            throw new SQLException("Conexión a la base de datos fallida");
        }

        // Verificar si la carrera existe y obtener carrera_id
        pstmtCheckCarrera = conn.prepareStatement(sqlCheckCarrera);
        pstmtCheckCarrera.setString(1, nombreCarrera);
        rs = pstmtCheckCarrera.executeQuery();

        if (rs.next()) {
            int carreraId = rs.getInt("carrera_id");

            // Verificar si el semestre ingresado existe para la carrera y obtener semestre_id
            pstmtCheckSemestre = conn.prepareStatement(sqlCheckSemestre);
            pstmtCheckSemestre.setInt(1, numeroSemestre);
            pstmtCheckSemestre.setInt(2, carreraId);
            ResultSet rsSemestre = pstmtCheckSemestre.executeQuery();

            if (rsSemestre.next()) {
                int semestreId = rsSemestre.getInt("semestre_id");

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
                        return true; // Retornar verdadero si la materia fue actualizada con éxito
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar la materia.");
                        return false;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El número de semestre especificado no es válido para la carrera dada.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "La carrera especificada no existe.");
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar la materia: " + e.getMessage());
        e.printStackTrace();
        return false; // Asegura que se retorne falso si ocurre una excepción
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmtCheckCarrera != null) {
                pstmtCheckCarrera.close();
            }
            if (pstmtCheckSemestre != null) {
                pstmtCheckSemestre.close();
            }
            if (pstmtCheckMateria != null) {
                pstmtCheckMateria.close();
            }
            if (pstmtUpdate != null) {
                pstmtUpdate.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }
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
    public List<Carrera> obtenerCarreras() {
    String sql = "SELECT carrera_id, nombre_carrera FROM carreras";
    List<Carrera> carreras = new ArrayList<>();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Connection conn = Conex.getConex();
        if (conn == null) {
            throw new SQLException("Conexión a la base de datos fallida");
        }
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("carrera_id");
            String nombre = rs.getString("nombre_carrera");
            carreras.add(new Carrera(id, nombre));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener carreras: " + e.getMessage());
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
    return carreras;
}
}
