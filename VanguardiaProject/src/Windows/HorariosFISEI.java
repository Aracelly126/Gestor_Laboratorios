/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

import java.sql.ResultSet;
import Codes.Bloques;
import Codes.FiltradorAulas_Lab;
import Codes.Horario;
import Codes.Horarios;
import Codes.Lab_Aulas;
import CodesBD.CrudReservas;
import CodesBD.Reserva;
import Utils.Conex;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class HorariosFISEI extends javax.swing.JPanel {

    private JPopupMenu popupMenu;

    /**
     * Creates new form HorariosFISEI
     */
    public HorariosFISEI() {
        initComponents();
        actualizarBloques();
        filtradoUno();
        configureJDateChooser();
        toggleComboBoxes(false);
        configurePopupMenu();

    }

    private void actualizarBloques() {
        Bloques bloquesDAO = new Bloques();
        List<String> bloques = bloquesDAO.BloquesUni();

        jcmbBloques.removeAllItems();
        for (String bloque : bloques) {
            jcmbBloques.addItem(bloque);
        }
    }

    public void filtradoUno() {
        String nombreBloque = (String) jcmbBloques.getSelectedItem();
        if (nombreBloque != null) {
            jcmbLabAulas.removeAllItems();
            List<String> tipos = FiltradorAulas_Lab.obtenerTiposPorBloque(nombreBloque);
            for (String tipo : tipos) {
                jcmbLabAulas.addItem(tipo);
            }
        }
    }

    public void filtroDos() {
    String nombreBloqueSeleccionado = (String) jcmbBloques.getSelectedItem();
    String tipoEspacioSeleccionado = (String) jcmbLabAulas.getSelectedItem();

    if (nombreBloqueSeleccionado == null || tipoEspacioSeleccionado == null) {
        return;  // No hacer nada si los valores seleccionados son nulos
    }

    Lab_Aulas labAulas = new Lab_Aulas();
    List<String> aulas = labAulas.obtenerAulasPorBloque(nombreBloqueSeleccionado, tipoEspacioSeleccionado);
    jcmbNumLabAulas.removeAllItems();
    for (String aula : aulas) {
        jcmbNumLabAulas.addItem(aula);
    }

    // Llama a insertar() solo si hay elementos en jcmbNumLabAulas
    if (jcmbNumLabAulas.getItemCount() > 0) {
        insertar();
    }
}


public void insertar() {
    Object bloqueSeleccionado = jcmbBloques.getSelectedItem();
    Object tipoEspacioSeleccionado = jcmbLabAulas.getSelectedItem();
    Object laboAulSeleccionado = jcmbNumLabAulas.getSelectedItem();

    if (bloqueSeleccionado == null || tipoEspacioSeleccionado == null || laboAulSeleccionado == null) {
        JOptionPane.showMessageDialog(null, "Por favor seleccione todos los campos antes de continuar.");
        return;
    }

    String nombreBloque = bloqueSeleccionado.toString();
    String tipoEspacio = tipoEspacioSeleccionado.toString();
    String laboAul = laboAulSeleccionado.toString();

    Horarios horarios = new Horarios();
    List<Horario> horariosList = horarios.obtenerAulasPorBloqueYSala(nombreBloque, tipoEspacio, laboAul);

    DefaultTableModel model = (DefaultTableModel) utcJTable1.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        for (int j = 1; j < model.getColumnCount(); j++) {
            model.setValueAt("", i, j);  // Limpiar la tabla antes de insertar nuevos datos
        }
    }
    for (Horario horario : horariosList) {
        String hora = horario.getHora();
        int fila = obtenerFilaPorHora(hora);
        if (fila >= 0) {
            if (horario.getLunes() != null && !horario.getLunes().isEmpty()) {
                model.setValueAt("Clase: " + horario.getLunes(), fila, 1);
            }
            if (horario.getMartes() != null && !horario.getMartes().isEmpty()) {
                model.setValueAt("Clase: " + horario.getMartes(), fila, 2);
            }
            if (horario.getMiercoles() != null && !horario.getMiercoles().isEmpty()) {
                model.setValueAt("Clase: " + horario.getMiercoles(), fila, 3);
            }
            if (horario.getJueves() != null && !horario.getJueves().isEmpty()) {
                model.setValueAt("Clase: " + horario.getJueves(), fila, 4);
            }
            if (horario.getViernes() != null && !horario.getViernes().isEmpty()) {
                model.setValueAt("Clase: " + horario.getViernes(), fila, 5);
            }
        } else {
            System.out.println("Hora no encontrada: " + hora);
        }
    }

    // Cargar los préstamos y mostrarlos en celeste
    CrudReservas crudReservas = new CrudReservas();
    List<Reserva> reservasList = crudReservas.obtenerReservasPorEspacio(laboAul);

    for (Reserva reserva : reservasList) {
        String hora = reserva.getHora();
        int fila = obtenerFilaPorHora(hora);
        int columna = obtenerColumnaPorFecha(reserva.getFecha());
        if (fila >= 0 && columna > 0) {  // Asegúrate de no sobrescribir la columna de la hora
            model.setValueAt("Préstamo: " + reserva.getObservacion(), fila, columna);
        }
    }

    utcJTable1.revalidate();
    utcJTable1.repaint();
}


private int obtenerColumnaPorFecha(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
    // Mapear de Calendar.DAY_OF_WEEK a columna en la tabla
    // domingo (Calendar.SUNDAY) es 1, lunes (Calendar.MONDAY) es 2, ...
    // pero en tu tabla, domingo probablemente no se usa y lunes debería ser la columna 1
    if (diaSemana == Calendar.SUNDAY) return -1; // Si es domingo, ignora ya que tu tabla probablemente no usa domingo
    return diaSemana - 1; // De Calendar.SUNDAY (1) a Calendar.SATURDAY (7), restar 1 para mapear a la tabla
}

private int obtenerFilaPorHora(String hora) {
    for (int i = 0; i < utcJTable1.getRowCount(); i++) {
        if (utcJTable1.getValueAt(i, 0).equals(hora)) {
            return i;
        }
    }
    return -1;
}

    private void configureJDateChooser() {
        jDateChooser2.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = jDateChooser2.getDate();
                    if (selectedDate != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(selectedDate);
                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                        if (dayOfWeek == Calendar.SUNDAY) {
                            JOptionPane.showMessageDialog(null, "No se puede seleccionar domingos.", "Fecha no válida", JOptionPane.WARNING_MESSAGE);
                            jDateChooser2.setDate(null);
                        }
                    }
                    toggleComboBoxes(selectedDate != null);
                }
            }
        });
    }

    private void toggleComboBoxes(boolean enable) {
        jcmbBloques.setEnabled(enable);
        jcmbLabAulas.setEnabled(enable);
        jcmbNumLabAulas.setEnabled(enable);
    }

private void configurePopupMenu() {
    popupMenu = new JPopupMenu();
    JMenuItem reservarItem = new JMenuItem("Reservar");
    JMenuItem modificarReservaItem = new JMenuItem("Modificar Reserva");
    JMenuItem eliminarReservaItem = new JMenuItem("Eliminar Reserva");

    reservarItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = utcJTable1.getSelectedRow();
            int col = utcJTable1.getSelectedColumn();
            if (row >= 0 && col >= 0) {
                String hora = (String) utcJTable1.getValueAt(row, 0); // Obteniendo la hora de la casilla seleccionada

                // Cambiar el valor de la celda seleccionada a "Reservado"
                utcJTable1.setValueAt("Reservado", row, col);

                // Obtener los datos seleccionados
                String fecha = ((JTextField) jDateChooser2.getDateEditor().getUiComponent()).getText();
                String bloque = (String) jcmbBloques.getSelectedItem();
                String tipoEspacio = (String) jcmbLabAulas.getSelectedItem();
                String numeroAula = (String) jcmbNumLabAulas.getSelectedItem();

                // Crear instancia del JFrame Reservar y pasar los datos
                Reservar reservarFrame = new Reservar();
                reservarFrame.setDatosReserva(fecha, bloque, tipoEspacio, numeroAula, hora); // Pasar la hora
                reservarFrame.consumirFecha(ObtenerFecha());
                reservarFrame.setVisible(true);
            }
        }
    });

    modificarReservaItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = utcJTable1.getSelectedRow();
            int col = utcJTable1.getSelectedColumn();
            if (row >= 0 && col >= 0) {
                String valorCelda = (String) utcJTable1.getValueAt(row, col);
                JOptionPane.showMessageDialog(null, "Modificando reserva en celda en fila " + row + ", columna " + col + " con valor: " + valorCelda);
            }
        }
    });

    eliminarReservaItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = utcJTable1.getSelectedRow();
            int col = utcJTable1.getSelectedColumn();
            if (row >= 0 && col >= 0) {
                // Eliminar la reserva de la celda seleccionada
                utcJTable1.setValueAt("", row, col);
                JOptionPane.showMessageDialog(null, "Reserva eliminada");
            }
        }
    });

    popupMenu.add(reservarItem);
    popupMenu.add(modificarReservaItem);
    popupMenu.add(eliminarReservaItem);

    utcJTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                showPopup(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                showPopup(e);
            }
        }

        private void showPopup(MouseEvent e) {
            int row = utcJTable1.rowAtPoint(e.getPoint());
            int col = utcJTable1.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
                utcJTable1.setRowSelectionInterval(row, row);
                utcJTable1.setColumnSelectionInterval(col, col);

                // Obtener la fecha seleccionada en el JDateChooser
                Date selectedDate = jDateChooser2.getDate();
                Date currentDate = new Date(); // Fecha actual

                // Comparar fechas
                boolean isSameDay = isSameDay(selectedDate, currentDate);
                String horaSeleccionada = (String) utcJTable1.getValueAt(row, 0);
                String horaActual = obtenerHoraActual();

                // Verificar si la celda ya está reservada
                String valorCelda = (String) utcJTable1.getValueAt(row, col);

                // Si la celda está reservada para una clase, mostrar aviso y salir
                if (valorCelda != null && valorCelda.toLowerCase().contains("clase")) {
                    JOptionPane.showMessageDialog(null, "Esta hora ya está reservada para una clase.");
                    return;
                }

                // Ajustar las opciones del popupMenu según el estado de la celda
                if (valorCelda != null && !valorCelda.isEmpty()) {
                    reservarItem.setEnabled(false);
                    modificarReservaItem.setEnabled(true);
                    eliminarReservaItem.setEnabled(true);
                } else if (isSameDay && esHoraValida(horaSeleccionada, horaActual)) {
                    reservarItem.setEnabled(true);
                    modificarReservaItem.setEnabled(false);
                    eliminarReservaItem.setEnabled(false);
                } else {
                    reservarItem.setEnabled(false);
                    modificarReservaItem.setEnabled(false);
                    eliminarReservaItem.setEnabled(false);
                }

                // Mostrar el menú emergente si es válido
                if (reservarItem.isEnabled() || modificarReservaItem.isEnabled() || eliminarReservaItem.isEnabled()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede Seleccionar horas Pasadas.");
                }
            }
        }
    });
}

private boolean isSameDay(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(date1);
    cal2.setTime(date2);
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
           cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
}

private String ObtenerFecha() {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    return formatoFecha.format(this.jDateChooser2.getCalendar().getTime());
}

private String obtenerHoraActual() {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    return sdf.format(new Date());
}

private boolean esHoraValida(String horaSeleccionada, String horaActual) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date horaSel = sdf.parse(horaSeleccionada.split("-")[0].trim());
        Date horaAct = sdf.parse(horaActual);
        return horaSel.after(horaAct); // Verifica que la hora seleccionada sea después de la hora actual
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jcmbBloques = new javax.swing.JComboBox<>();
        jcmbLabAulas = new javax.swing.JComboBox<>();
        jcmbNumLabAulas = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        utcJTable1 = new ComponentesPropios.utcJTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setText("Espacios Aulas/Laboratorios");
        add(jLabel2);

        jcmbBloques.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbBloquesFocusLost(evt);
            }
        });
        jcmbBloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcmbBloquesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jcmbBloquesMousePressed(evt);
            }
        });
        jcmbBloques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbBloquesActionPerformed(evt);
            }
        });
        jcmbBloques.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbBloquesKeyPressed(evt);
            }
        });
        add(jcmbBloques);

        jcmbLabAulas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcmbLabAulasMouseClicked(evt);
            }
        });
        jcmbLabAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbLabAulasActionPerformed(evt);
            }
        });
        add(jcmbLabAulas);

        jcmbNumLabAulas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcmbNumLabAulasMouseClicked(evt);
            }
        });
        jcmbNumLabAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbNumLabAulasActionPerformed(evt);
            }
        });
        add(jcmbNumLabAulas);
        add(jDateChooser2);

        jScrollPane1.setViewportView(utcJTable1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbBloquesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbBloquesFocusLost

    }//GEN-LAST:event_jcmbBloquesFocusLost

    private void jcmbBloquesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbBloquesMouseClicked

    }//GEN-LAST:event_jcmbBloquesMouseClicked

    private void jcmbBloquesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbBloquesMousePressed

    }//GEN-LAST:event_jcmbBloquesMousePressed

    private void jcmbBloquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbBloquesActionPerformed

    }//GEN-LAST:event_jcmbBloquesActionPerformed

    private void jcmbBloquesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbBloquesKeyPressed

    }//GEN-LAST:event_jcmbBloquesKeyPressed

    private void jcmbLabAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbLabAulasMouseClicked
        filtradoUno();
    }//GEN-LAST:event_jcmbLabAulasMouseClicked

    private void jcmbLabAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbLabAulasActionPerformed

    }//GEN-LAST:event_jcmbLabAulasActionPerformed

    private void jcmbNumLabAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbNumLabAulasMouseClicked
        filtroDos();
        
    }//GEN-LAST:event_jcmbNumLabAulasMouseClicked

    private void jDateChooser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked

    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseClicked

    }//GEN-LAST:event_jDateChooser2MouseClicked

    private void jcmbNumLabAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbNumLabAulasActionPerformed
        insertar();
    }//GEN-LAST:event_jcmbNumLabAulasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcmbBloques;
    private javax.swing.JComboBox<String> jcmbLabAulas;
    private javax.swing.JComboBox<String> jcmbNumLabAulas;
    private ComponentesPropios.utcJTable utcJTable1;
    // End of variables declaration//GEN-END:variables
}
