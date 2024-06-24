/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropios;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class TablaReservas extends JTable {
    public TablaReservas() {
        super(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Fecha", "Hora", "Bloque", "Tipo de Espacio", "Laboratorio/Aula"}
        ));
    }

    public void agregarReserva(Object[] reserva) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(reserva);
    }
}
