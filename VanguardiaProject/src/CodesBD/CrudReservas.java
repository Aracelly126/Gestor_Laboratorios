/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodesBD;

import Utils.Conex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CrudReservas {

    public static boolean guardarReserva(String Fecha, String id, String Horas, String cedula, String nombre, String correo, String descripcion) {
        String sql = "INSERT INTO prestamos (Fecha, HORAS_PRESTAMO,cedula, nombre_usuario, correo, observacion,ID_ESPACIO_PERTENECE ) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = Conex.getConex();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Fecha);
            pstmt.setString(2, Horas);
            pstmt.setString(3, cedula);
            pstmt.setString(4, nombre);
            pstmt.setString(5, correo);
            pstmt.setString(6, descripcion);
            pstmt.setString(7, id);

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la reserva.");
            return false;
        }
    }

}
