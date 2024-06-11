/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JTable;
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
public class crud_editarProfesor {
    public void editarProfesor(String nombre, int idEspacio, int idMateria, int idProfesor) throws Exception {
        String updateQuery = "UPDATE profesores SET nombre = ?, fk_espacio = ?, fk_materia = ? WHERE id_profesor = ?";

        try (Connection connection = (Connection) Conex.getConex();
             PreparedStatement updateStmt = (PreparedStatement) connection.prepareStatement(updateQuery)) {

            updateStmt.setString(1, nombre);
            updateStmt.setInt(2, idEspacio);
            updateStmt.setInt(3, idMateria);
            updateStmt.setInt(4, idProfesor);
            updateStmt.executeUpdate();
        }
    }

    public ResultSet obtenerProfesor(int idProfesor) throws Exception {
        String selectQuery = "SELECT * FROM profesores WHERE id_profesor = ?";
        ResultSet resultSet = null;

        try (Connection connection = (Connection) Conex.getConex();
             PreparedStatement selectStmt = (PreparedStatement) connection.prepareStatement(selectQuery)) {

            selectStmt.setInt(1, idProfesor);
            resultSet = selectStmt.executeQuery();
        }

        return resultSet;
    }
}
