/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author USER
 */
public class Crud_AgregarProfesor {
    crud_profesores cs = new crud_profesores();
    public void addProfesor(String nombre, String materiaSeleccionada, String bloqueSeleccionado, String tipoSeleccionado, String aulaSeleccionada, JTable table) throws SQLException {
    String idblock = "SELECT ID_BLOQUE FROM BLOQUES WHERE NOMBRE = ?";
    String espacioQuery = "SELECT ID_ESPACIO FROM espacios WHERE NOMBRE = ? AND ID_BLOQUE_PERTENECE = ?";
    String materiaQuery = "SELECT materia_id FROM materias WHERE nombre_materia = ?";
    String insertQuery = "INSERT INTO profesores (nombre, fk_espacio, fk_materia) VALUES (?, ?, ?)";

    try (Connection connection = Conex.getConex()) {
        // obtener id del bloque
        int idblo = 0;
        try (PreparedStatement bloqueStmt = connection.prepareStatement(idblock)) {
            bloqueStmt.setString(1, bloqueSeleccionado);
            ResultSet bloqueRs = bloqueStmt.executeQuery();
            if (bloqueRs.next()) {
                idblo = bloqueRs.getInt("ID_BLOQUE");
            } else {
                throw new SQLException("No se encontró ninguna ID para el bloque seleccionado.");
            }
        }
        int espacioId = 0;
        try (PreparedStatement espacioStmt = connection.prepareStatement(espacioQuery)) {
            espacioStmt.setString(1, aulaSeleccionada);
            espacioStmt.setInt(2, idblo);
            ResultSet espacioRs = espacioStmt.executeQuery();
            if (espacioRs.next()) {
                espacioId = espacioRs.getInt("ID_ESPACIO");
            } else {
                throw new SQLException("No se encontró el espacio para el tipo y bloque proporcionados.");
            }
        }
        int materiaId = 0;
        try (PreparedStatement materiaStmt = connection.prepareStatement(materiaQuery)) {
            materiaStmt.setString(1, materiaSeleccionada);
            ResultSet materiaRs = materiaStmt.executeQuery();
            if (materiaRs.next()) {
                materiaId = materiaRs.getInt("materia_id");
            } else {
                throw new SQLException("No se encontró la materia para el nombre proporcionado.");
            }
        }
        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, nombre);
            insertStmt.setInt(2, espacioId);
            insertStmt.setInt(3, materiaId);
            insertStmt.executeUpdate();
            cs.cargarTabla(table);
        }
    }
}
}
