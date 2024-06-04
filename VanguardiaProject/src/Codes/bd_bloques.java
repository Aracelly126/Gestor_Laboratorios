package Codes;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.Conex;
import java.util.ArrayList;
import java.util.List;

public class bd_bloques {

    public boolean crearBloque(String nombre) {
        String sqlCheck = "SELECT * FROM BLOQUES WHERE NOMBRE = ?";
        String sqlInsert = "INSERT INTO BLOQUES (NOMBRE) VALUES (?)";
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
                JOptionPane.showMessageDialog(null, "El bloque ya existe en la base de datos.");
                return false;
            } else {
                pstmtInsert = (PreparedStatement) conn.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, nombre);
                int rowsInserted = pstmtInsert.executeUpdate();
                
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Bloque agregado con éxito.");
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el bloque: " + e.getMessage());
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

    public List<Bloque> obtenerBloques() {
        String sql = "SELECT * FROM BLOQUES";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Bloque> bloques = new ArrayList<>();

        try {
            Connection conn = Conex.getConex();
            if (conn == null) {
                throw new SQLException("Conexión a la base de datos fallida");
            }
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_BLOQUE");
                String nombre = rs.getString("NOMBRE");
                bloques.add(new Bloque(id, nombre));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener bloques: " + e.getMessage());
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
}
