package Codes;

import Utils.Conex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class bd_usuarios {

    public boolean crearUsuario(String correo, String clave) {
        String sqlCheck = "SELECT * FROM usuario WHERE correo = ?";
        String sqlInsert = "INSERT INTO usuario (correo, clave) VALUES (?, ?)";
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rs = null;

        try {
            Connection conn = Conex.getConex();
            if (conn == null) {
                throw new SQLException("Conexión a la base de datos fallida");
            }

            pstmtCheck = conn.prepareStatement(sqlCheck);
            pstmtCheck.setString(1, correo);
            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe en la base de datos.");
                return false;
            } else {
                pstmtInsert = conn.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, correo);
                pstmtInsert.setString(2, clave);
                int rowsInserted = pstmtInsert.executeUpdate();

                if (rowsInserted > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + e.getMessage());
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
}
