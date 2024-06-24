/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codes;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.Conex;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arita
 */
public class bd_carreras {

    public boolean crearCarrera(String nombre) {
        String sql = "SELECT * FROM CARRERAS WHERE NOMBRE_CARRERA = ?";
        String sqlInsert = "INSERT INTO CARRERAS (NOMBRE_CARRERA) VALUES (?)";
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rs = null;

        try {
            Connection conn = Conex.getConex();
            if (conn == null) {
                throw new SQLException("Conexión a la base de datos fallida");
            }

            pstmtCheck = (PreparedStatement) conn.prepareStatement(sql);
            pstmtCheck.setString(1, nombre);
            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "La carrera ya existe en la base de datos.");

                return false;
            } else {
                pstmtInsert = (PreparedStatement) conn.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, nombre);
                int rowsInserted = pstmtInsert.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Carrera agregada con éxito.");
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar La carrera " + e.getMessage());
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

    public List<Carreras> obtenerCarreras() {
        String sql = "SELECT * FROM CARRERAS";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Carreras> bloques = new ArrayList<>();

        try {
            Connection conn = Conex.getConex();
            if (conn == null) {
                throw new SQLException("Conexión a la base de datos fallida");
            }
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("CARRERA_ID");
                String nombre = rs.getString("NOMBRE_CARRERA");
                bloques.add(new Carreras(id, nombre));
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
        return bloques;
    }

    public boolean editarCarrera(int idCarrera, String nuevoNombre) {
        String sql = "UPDATE carreras SET NOMBRE_CARRERA = ? WHERE CARRERA_ID = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = Conex.getConex();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNombre);
            pstmt.setInt(2, idCarrera);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar la carrera: " + e.getMessage());
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

    public boolean eliminarCarrera(int idCarrera) {
        String sql = "DELETE FROM carreras WHERE CARRERA_ID = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = Conex.getConex();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, idCarrera);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Carrera eliminada con éxito.");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la carrera: " + e.getMessage());
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
