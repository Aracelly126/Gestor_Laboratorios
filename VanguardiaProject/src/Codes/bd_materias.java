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

    public boolean crearMateria(String nombre, int semestreId) {
        String sqlCheck = "SELECT * FROM materias WHERE nombre_materia = ?";
        String sqlInsert = "INSERT INTO materias (nombre_materia, semestre_id) VALUES (?, ?)";
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rs = null;

        try {
            Connection conn = Conex.getConex();
            if (conn == null) {
                throw new SQLException("Conexión a la base de datos fallida");
            }

            pstmtCheck = (PreparedStatement) conn.prepareStatement(sqlCheck);
            pstmtCheck.setString(1, nombre);
            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "La materia ya existe en la base de datos.");
                return false;
            } else {
                pstmtInsert = (PreparedStatement) conn.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, nombre);
                pstmtInsert.setInt(2, semestreId);
                int rowsInserted = pstmtInsert.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Materia agregada con éxito.");
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la materia: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmtCheck != null) {
                    pstmtCheck.close();
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
        String sql = "SELECT * FROM materias";
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
                int id = rs.getInt("materia_id");
                String nombre = rs.getString("nombre_materia");
                int semestreId = rs.getInt("semestre_id");
                materias.add(new Materia(id, nombre, semestreId));
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

    public boolean editarMateria(int idMateria, String nuevoNombre, int semestreId) {
        String sql = "UPDATE materias SET nombre_materia = ?, semestre_id = ? WHERE materia_id = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = Conex.getConex();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNombre);
            pstmt.setInt(2, semestreId);
            pstmt.setInt(3, idMateria);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar la materia: " + e.getMessage());
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
