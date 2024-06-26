/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class crud_asignacion {
     public void cargarProfesor(JComboBox<String> comboProfesor) {
    try {
        Statement statement = Conex.getConex().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT nombre, apellido FROM profesores"); // Corregido el nombre de la tabla
        comboProfesor.removeAllItems();
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            comboProfesor.addItem(nombre + " " + apellido); // Agregar nombre completo al combo box
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
      
 public void cargarMaterias(JComboBox<String> comboMaterias) {
        try {
            Connection con = Conex.getConex();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nombre_materia FROM materias");
            comboMaterias.removeAllItems();
            while (resultSet.next()) {
                String nombreMateria = resultSet.getString("nombre_materia");
                comboMaterias.addItem(nombreMateria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar materias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
