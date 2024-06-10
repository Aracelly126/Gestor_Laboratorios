/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class crud_profesores {

    public void cargarMaterias(JComboBox<String> comboMaterias, JComboBox<String> comboBloque, JComboBox<String> comboTipo) {
        try {
            String selectedBloque = (String) comboBloque.getSelectedItem();
            String selectedTipo = (String) comboTipo.getSelectedItem();

            if (selectedBloque == null || selectedTipo == null) {
                return; // No hacer nada si no hay bloque o tipo seleccionado
            }

            Connection cc = Conex.getConex();
            String idBloqueSql = "SELECT ID_BLOQUE FROM bloques WHERE NOMBRE = ?";
            PreparedStatement idBloqueStmt = cc.prepareStatement(idBloqueSql);
            idBloqueStmt.setString(1, selectedBloque);
            ResultSet idBloqueRs = idBloqueStmt.executeQuery();

            if (idBloqueRs.next()) {
                int idBloque = idBloqueRs.getInt("ID_BLOQUE");

                String sql = "SELECT nombre_materia FROM materias WHERE materia_id IN (SELECT fk_materia FROM horarios h INNER JOIN espacios e ON h.fk_espacio = e.ID_ESPACIO WHERE e.ID_BLOQUE_PERTENECE = ? AND e.TIPO = ?)";
                PreparedStatement psd = cc.prepareStatement(sql);
                psd.setInt(1, idBloque);
                psd.setString(2, selectedTipo);
                ResultSet rs = psd.executeQuery();

                comboMaterias.removeAllItems();
                while (rs.next()) {
                    comboMaterias.addItem(rs.getString("nombre_materia"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void llenarComboBoxAulas(JComboBox cbxAulasLab, JComboBox cbxTipo, JComboBox cbxCurso) {
        cbxAulasLab.removeAllItems();
        cbxAulasLab.addItem("Seleccionar");
        if (cbxCurso.getSelectedItem().equals("Seleccionar")) {
            return;
        }
        try {
            Connection cc = Conex.getConex();
            String idBloqueSql = "SELECT ID_BLOQUE FROM vanguardia.bloques WHERE NOMBRE = ?";
            PreparedStatement idBloqueStmt = cc.prepareStatement(idBloqueSql);
            idBloqueStmt.setString(1, cbxCurso.getSelectedItem().toString());
            ResultSet idBloqueRs = idBloqueStmt.executeQuery();

            if (idBloqueRs.next()) {
                int idBloque = idBloqueRs.getInt("ID_BLOQUE");

                String tipo = cbxTipo.getSelectedItem().toString();
                String sql = "SELECT NOMBRE FROM vanguardia.espacios WHERE TIPO = ? AND ID_BLOQUE_PERTENECE = ?";
                PreparedStatement psd = cc.prepareStatement(sql);
                psd.setString(1, tipo.equals("LABORATORIO") ? "LABORATORIO" : "AULA");
                psd.setInt(2, idBloque);
                ResultSet rs = psd.executeQuery();

                while (rs.next()) {
                    String aulaLab = rs.getString("NOMBRE");
                    cbxAulasLab.addItem(aulaLab);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar el combo box de aulas/laboratorios: " + e.getMessage());
        }
    }

    public void addProfesor(String nombre, String materiaSeleccionada, String bloqueSeleccionado) {
        String query = "INSERT INTO profesores (nombre, fk_espacio, fk_materia) VALUES (?, (SELECT ID_ESPACIO FROM espacios WHERE NOMBRE = ? AND ID_BLOQUE_PERTENECE = (SELECT ID_BLOQUE FROM bloques WHERE NOMBRE = ?)), (SELECT materia_id FROM materias WHERE nombre_materia = ?))";

        try ( Connection connection = Conex.getConex();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, bloqueSeleccionado);
            preparedStatement.setString(3, bloqueSeleccionado);
            preparedStatement.setString(4, materiaSeleccionada);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
    
}
