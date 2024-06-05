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

    public void cargarBloque(JComboBox<String> comboBloque) {
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

    public void crearEspacio(JComboBox<String> comboTipo, JTextField txtNombre, JComboBox<String> comboBloque, JTable table) {
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
            insertStatement.setString(2, nombre.toUpperCase());
            insertStatement.setInt(3, bloqueId);
            insertStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Espacio creado con éxito.");
            cargarTabla(table);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el espacio.");
        }
    }

    public void cargarTabla(JTable table) {
        try {
            String query = "SELECT e.TIPO, e.NOMBRE, b.NOMBRE AS BLOQUE FROM espacios e "
                    + "JOIN bloques b ON e.ID_BLOQUE_PERTENECE = b.ID_BLOQUE";
            PreparedStatement preparedStatement = Conex.getConex().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
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

    public void actualizarEspacio(int idEspacio, JComboBox<String> comboTipo, JTextField txtNombre, JComboBox<String> comboBloque, JTable table) {
        String tipo = (String) comboTipo.getSelectedItem();
        String nombre = txtNombre.getText();
        String bloque = (String) comboBloque.getSelectedItem();
        System.out.println(idEspacio);

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

            String updateQuery = "UPDATE espacios SET TIPO = ?, NOMBRE = ?, ID_BLOQUE_PERTENECE = ? WHERE ID_ESPACIO = ?";
            PreparedStatement updateStatement = Conex.getConex().prepareStatement(updateQuery);
            updateStatement.setString(1, tipo);
            updateStatement.setString(2, nombre.toUpperCase());
            updateStatement.setInt(3, bloqueId);
            updateStatement.setInt(4, idEspacio);
            updateStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Espacio actualizado con éxito.");
            cargarTabla(table);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el espacio.");
        }
    }

    public int obtenerIdEspacio(String nombreEspacio, String nombreBloque) {
        int idEspacio = -1;
        try {
            String query = "SELECT ID_ESPACIO FROM espacios e "
                    + "JOIN bloques b ON e.ID_BLOQUE_PERTENECE = b.ID_BLOQUE "
                    + "WHERE e.NOMBRE = ? AND b.NOMBRE = ?";
            PreparedStatement preparedStatement = Conex.getConex().prepareStatement(query);
            preparedStatement.setString(1, nombreEspacio);
            preparedStatement.setString(2, nombreBloque);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idEspacio = resultSet.getInt("ID_ESPACIO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEspacio;
    }
}
