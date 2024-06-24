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

    public void addProfesor(String identificador, String cedula, String nombre, String apellido, JTable table) {
        String insertQuery = "INSERT INTO profesores (identificador, cedula, nombre, apellido) VALUES (?, ?, ?, ?)";

        Connection connection = Conex.getConex();

        try {
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setString(1, identificador);
            insertStmt.setString(2, cedula);
            insertStmt.setString(3, nombre);
            insertStmt.setString(4, apellido);
            insertStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Profesor Agregado Correctamente");
            cargarTabla(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProfesor(String identificador, JTable table) {
        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar al profesor con identificador " + identificador + "?",
                "Confirmación de Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM profesores WHERE identificador = ?";
                PreparedStatement statement = Conex.getConex().prepareStatement(sql);
                statement.setString(1, identificador);
                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null,"El profesor con identificador " + identificador + " ha sido eliminado correctamente.");
                    cargarTabla(table);
                } else {
                    JOptionPane.showMessageDialog(null,"No se encontró ningún profesor con el identificador " + identificador);
                }
            } catch (SQLException e) {
                System.err.println("Error al eliminar al profesor: " + e.getMessage());
            }
        }
    }

    public void editarProfesor(String identificador, String cedula, String nombre, String apellido, String identificadorProfesor) {
        int idProfesor = obtenerIdProfesor(identificadorProfesor);

        if (idProfesor == -1) {
            System.out.println("No se encontró ningún profesor con el identificador proporcionado.");
            return;
        }

        String updateQuery = "UPDATE profesores SET identificador = ?, cedula = ?, nombre = ?, apellido = ? WHERE id = ?";

        try {
            Connection connection = Conex.getConex();
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setString(1, identificador);
            updateStmt.setString(2, cedula);
            updateStmt.setString(3, nombre);
            updateStmt.setString(4, apellido);
            updateStmt.setInt(5, idProfesor);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int obtenerIdProfesor(String identificadorProfesor) {
        int idProfesor = -1;
        String query = "SELECT id FROM profesores WHERE identificador = ?";
        try {
            Connection connection = Conex.getConex();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, identificadorProfesor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idProfesor = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idProfesor;
    }

    public String obtenerUltimoIdentificador() {
        String ultimoIdentificador = null;
        String query = "SELECT identificador FROM profesores ORDER BY identificador DESC LIMIT 1";

        try {
            Connection connection = Conex.getConex();
            PreparedStatement stmt = connection.prepareStatement(query);
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
