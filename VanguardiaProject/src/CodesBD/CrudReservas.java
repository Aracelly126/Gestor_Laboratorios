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

    public static boolean guardarReserva(String cedula, String nombre, String correo, String descripcion) {
        String sql = "INSERT INTO reservas (cedula, nombre, correo, descripcion) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conex.getConex();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cedula);
            pstmt.setString(2, nombre);
            pstmt.setString(3, correo);
            pstmt.setString(4, descripcion);

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la reserva.");
            return false;
        }
    }

    // Aquí puedes agregar más métodos CRUD (Actualizar, Eliminar, Recuperar)
}
