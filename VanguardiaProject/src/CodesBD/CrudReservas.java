/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodesBD;

import Utils.Conex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;

public class CrudReservas {

   public static boolean guardarReserva(String Fecha, String id, String Horas, String cedula, String nombre, String correo, String descripcion) {
        String sql = "INSERT INTO prestamos (Fecha, HORAS_PRESTAMO,cedula, nombre_usuario, correo, observacion,ID_ESPACIO_PERTENECE ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = Conex.getConex();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
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

    // Añadir este método
    public List<Reserva> obtenerReservasPorEspacio(String idEspacio) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE ID_ESPACIO_PERTENECE = ?";
        Connection conn = Conex.getConex();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idEspacio);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setFecha(rs.getDate("Fecha"));
                reserva.setHora(rs.getString("HORAS_PRESTAMO"));
                reserva.setCedula(rs.getString("cedula"));
                reserva.setNombreUsuario(rs.getString("nombre_usuario"));
                reserva.setCorreo(rs.getString("correo"));
                reserva.setObservacion(rs.getString("observacion"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener reservas.");
        }
        return reservas;
    }

}
