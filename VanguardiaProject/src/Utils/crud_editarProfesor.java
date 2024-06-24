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
