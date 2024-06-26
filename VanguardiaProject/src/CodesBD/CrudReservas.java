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

//    public static boolean eliminarReserva(String prestamoId) {
//        String sql = "DELETE FROM prestamos WHERE ID_ESPACIO_PERTENECE = ?";
//        Connection conn = Conex.getConex();
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, prestamoId);
//            int filasEliminadas = pstmt.executeUpdate();
//            return filasEliminadas > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error al eliminar la reserva.");
//            return false;
//        }
//    }

    public static boolean actualizarReserva(String id, String cedula, String nombre, String correo, String descripcion) {
        // Definimos la consulta SQL con una cláusula WHERE que especifica el ID del espacio
        String sql = "UPDATE prestamos SET cedula = ?, nombre_usuario = ?, correo = ?, observacion = ? WHERE ID_ESPACIO_PERTENECE = ?";
        try ( Connection conn = Conex.getConex(); // Intentamos obtener una conexión
                  PreparedStatement pstmt = conn.prepareStatement(sql)) { // Preparamos la declaración
            // Asignamos los valores a los parámetros de la consulta
            pstmt.setString(1, cedula);
            pstmt.setString(2, nombre);
            pstmt.setString(3, correo);
            pstmt.setString(4, descripcion);
            pstmt.setString(5, id); // Aseguramos que solo se actualice la fila que coincida con este ID

            // Ejecutamos la actualización y verificamos si se afectaron filas
            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0; // Retorna true si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la reserva.");
            return false; // Retorna false si ocurre alguna excepción
        }
    }

    public static Reserva obtenerReservaPorId(String prestamoId) {
        String sql = "SELECT cedula, nombre_usuario, correo, observacion FROM prestamos WHERE ID_PRESTAMO = ?";
        Connection conn = Conex.getConex();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prestamoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setCedula(rs.getString("cedula"));
                reserva.setNombreUsuario(rs.getString("nombre_usuario"));
                reserva.setCorreo(rs.getString("correo"));
                reserva.setObservacion(rs.getString("observacion"));
                return reserva;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null; // Considera retornar null o manejar de otra manera si no hay datos.
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
