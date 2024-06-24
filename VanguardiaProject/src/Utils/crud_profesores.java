package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class crud_profesores {

   

    public void cargarTabla(JTable table) {
        try {
            String query = "SELECT identificador, cedula, nombre, apellido FROM profesores";
            PreparedStatement preparedStatement = Conex.getConex().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                String identificador = resultSet.getString("identificador");
                String cedula = resultSet.getString("cedula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                model.addRow(new Object[]{identificador, cedula, nombre, apellido});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public String obtenerUltimoIdentificador() {
        String ultimoIdentificador = null;
        String query = "SELECT identificador FROM profesores ORDER BY identificador DESC LIMIT 1";

        try ( Connection connection = Conex.getConex();  PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ultimoIdentificador = rs.getString("identificador");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoIdentificador;
    }

    // Método para generar el siguiente identificador
    public String generarSiguienteIdentificador(String ultimoIdentificador) {
        if (ultimoIdentificador == null || ultimoIdentificador.isEmpty()) {
            return "PF001";
        }

        String prefix = ultimoIdentificador.substring(0, 2);
        int numero = Integer.parseInt(ultimoIdentificador.substring(2));
        numero++;

        return prefix + String.format("%03d", numero);
    }

    // Método para actualizar el label del identificador
    public void actualizarLabelIdentificador(JTextField labelIdentificador) {
        String ultimoIdentificador = obtenerUltimoIdentificador();
        String siguienteIdentificador = generarSiguienteIdentificador(ultimoIdentificador);
        labelIdentificador.setText(siguienteIdentificador);
    }

}
