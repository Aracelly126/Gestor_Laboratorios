/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class crud_bloques {

    public void cargarBloque(JComboBox<String> comboBloque){
        try {
            Statement statement = Conex.getConex().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOMBRE FROM bloques");
            comboBloque.removeAllItems();
            while (resultSet.next()) {
                comboBloque.addItem(resultSet.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearEspacio(JComboBox<String> comboTipo, JTextField txtNombre, JComboBox<String> comboBloque) {
        String tipo = (String) comboTipo.getSelectedItem();
        String nombre = txtNombre.getText();
        String bloque = (String) comboBloque.getSelectedItem();

        if (tipo == null || nombre.isEmpty() || bloque == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return;
        }

        try {
            String bloqueQuery = "SELECT ID_BLOQUE FROM bloques WHERE NOMBRE = ?";
            PreparedStatement bloqueStatement = Conex.getConex().prepareStatement(bloqueQuery);
            bloqueStatement.setString(1, bloque);
            ResultSet bloqueResultSet = bloqueStatement.executeQuery();
            int bloqueId = -1;
            if (bloqueResultSet.next()) {
                bloqueId = bloqueResultSet.getInt("ID_BLOQUE");
            }

            if (bloqueId == -1) {
                JOptionPane.showMessageDialog(null, "Bloque no encontrado.");
                return;
            }

            String insertQuery = "INSERT INTO espacios (TIPO, NOMBRE, ID_BLOQUE_PERTENECE) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = Conex.getConex().prepareStatement(insertQuery);
            insertStatement.setString(1, tipo);
            insertStatement.setString(2, nombre);
            insertStatement.setInt(3, bloqueId);
            insertStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Espacio creado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el espacio.");
        }
    }

    public void cargarTabla(JTable table) {
        try {
            String query = "SELECT e.TIPO, e.NOMBRE, b.NOMBRE AS BLOQUE FROM espacios e " +
                           "JOIN bloques b ON e.ID_BLOQUE_PERTENECE = b.ID_BLOQUE";
            PreparedStatement preparedStatement = Conex.getConex().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Obtiene el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Limpia la tabla existente
            model.setRowCount(0);

            // Añade filas a la tabla
            while (resultSet.next()) {
                String tipo = resultSet.getString("TIPO");
                String nombre = resultSet.getString("NOMBRE");
                String bloque = resultSet.getString("BLOQUE");
                model.addRow(new Object[]{tipo, nombre, bloque});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
