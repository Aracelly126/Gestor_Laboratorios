package Codes;

import Utils.Conex;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.swing.JOptionPane;

public class bd_login {

    public bd_login() {

    }

    public boolean login(String correo, String clave) {
        String sql = "SELECT * FROM USUARIO WHERE correo = ? AND clave = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = (PreparedStatement) Conex.getConex().prepareStatement(sql);
            pstmt.setString(1, correo);
            pstmt.setString(2, clave);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login fail: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Resource close fail: " + e.getMessage());
            }
        }
        return false;
    }
}
