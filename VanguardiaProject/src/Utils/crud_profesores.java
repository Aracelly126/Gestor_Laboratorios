/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JComboBox;

/**
 *
 * @author thexe
 */
public class crud_profesores {
    
    public static void loadMaterias(JComboBox<String> comboBox) {
        try (Connection connection = Conex.getConex();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT materia_id, nombre FROM materias")) {

            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
