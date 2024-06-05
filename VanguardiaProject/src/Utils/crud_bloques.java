/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class crud_bloques {

    public void cargarBloque(JComboBox comboBloque){
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
    
   public void cargarHoras(JComboBox comboBloque, JComboBox comboDia, JComboBox comboHora){
       if (comboBloque.getSelectedItem() == null || comboDia.getSelectedItem() == null) {
            return;
        }

        String bloqueSeleccionado = (String) comboBloque.getSelectedItem();
        String diaSeleccionado = (String) comboDia.getSelectedItem();

        ArrayList<String> horasOcupadas = new ArrayList<>();
        try {
            String query = "SELECT hora FROM horarios h " +
                           "JOIN bloques b ON h.fk_espacio = b.ID_BLOQUE " +
                           "WHERE b.NOMBRE = ? AND h." + diaSeleccionado.toLowerCase() + " = 1";
            PreparedStatement preparedStatement = Conex.getConex().prepareStatement(query);
            preparedStatement.setString(1, bloqueSeleccionado);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                horasOcupadas.add(resultSet.getString("hora"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Horas disponibles (puedes ajustar esto según las horas que manejes)
        String[] todasLasHoras = {"7:00 - 8:00", "8:00 - 9:00", "9:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00"
                                  , "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00"};

        comboHora.removeAllItems();
        for (String hora : todasLasHoras) {
            if (!horasOcupadas.contains(hora)) {
                comboHora.addItem(hora);
            }
        }
   }



    public void crearEspacio(String tipo, String nombre, int idBloque, String dia, String hora) {
        
    }
}


