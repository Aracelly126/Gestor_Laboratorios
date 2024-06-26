package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class crud_asignacion {
    
   
    public void cargarProfesor(JComboBox<String> comboProfesor) {
        try {
            Connection con = Conex.getConex();
            String query = "SELECT id_profesor, nombre, apellido FROM profesores";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            comboProfesor.removeAllItems();
            while (resultSet.next()) {
                int idProfesor = resultSet.getInt("id_profesor");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                comboProfesor.addItem(nombre + " " + apellido);
                comboProfesor.putClientProperty(nombre + " " + apellido, idProfesor); // Guardar el ID en las propiedades del combo box
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar profesores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void asignarProfesor(String nombreCompleto, int idHorario) {
        try {
            Connection con = Conex.getConex();
            String[] nombreApellido = nombreCompleto.split(" ");
            String nombre = nombreApellido[0];
            String apellido = nombreApellido[1];
            String query = "SELECT id_profesor FROM profesores WHERE nombre = ? AND apellido = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int idProfesor = rs.getInt("id_profesor");
                String updateQuery = "UPDATE horarios SET id_profesor = ? WHERE id_horario = ?";
                PreparedStatement updatePst = con.prepareStatement(updateQuery);
                updatePst.setInt(1, idProfesor);
                updatePst.setInt(2, idHorario);
                updatePst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Profesor asignado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el profesor seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al asignar profesor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Método adicional para obtener el ID del profesor desde el combo box
    public int obtenerIdProfesorDesdeComboBox(JComboBox<String> comboProfesor) {
        String nombreCompleto = (String) comboProfesor.getSelectedItem();
        return (int) comboProfesor.getClientProperty(nombreCompleto);
    }
    
    
    
    // Método para cargar materias en un JComboBox
    public void cargarMaterias(JComboBox<String> comboMaterias) {
        try {
            Connection con = Conex.getConex();
            String query = "SELECT nombre_materia FROM materias";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
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
    
    // Otros métodos CRUD (crear, actualizar, eliminar) pueden ser implementados aquí
    
}
