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
        Lab_Aulas labAulas = new Lab_Aulas();
        List<String> aulas = labAulas.obtenerAulasPorBloque(nombreBloqueSeleccionado, tipoEspacioSeleccionado);
        jcmbNumLabAulas.removeAllItems();
        for (String aula : aulas) {
            jcmbNumLabAulas.addItem(aula);
        }
    }

    public void insertar() {
        Object bloqueSeleccionado = jcmbBloques.getSelectedItem();
        Object tipoEspacioSeleccionado = jcmbLabAulas.getSelectedItem();
        Object laboAulSeleccionado = jcmbNumLabAulas.getSelectedItem();

        String nombreBloque = bloqueSeleccionado.toString();
        String tipoEspacio = tipoEspacioSeleccionado.toString();
        String laboAul = (laboAulSeleccionado != null) ? laboAulSeleccionado.toString() : "";

        Horarios horarios = new Horarios();
        List<Horario> horariosList = horarios.obtenerAulasPorBloqueYSala(nombreBloque, tipoEspacio, laboAul);
        DefaultTableModel model = (DefaultTableModel) utcJTable1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 1; j < model.getColumnCount(); j++) {
                model.setValueAt("", i, j);
            }
        }
        for (Horario horario : horariosList) {
            System.out.println("Insertando horario: " + horario.getHora());
            String hora = horario.getHora();
            int fila = obtenerFilaPorHora(hora);
            if (fila >= 0) {
                if (horario.getLunes() != null && !horario.getLunes().isEmpty()) {
                    model.setValueAt("Reservado: " + "\n" + horario.getLunes(), fila, 1);
                }
                if (horario.getMartes() != null && !horario.getMartes().isEmpty()) {
                    model.setValueAt("Reservado: " + "\n" + horario.getMartes(), fila, 2);
                }
                if (horario.getMiercoles() != null && !horario.getMiercoles().isEmpty()) {
                    model.setValueAt("Reservado: " + "\n" + horario.getMiercoles(), fila, 3);
                }
                if (horario.getJueves() != null && !horario.getJueves().isEmpty()) {
                    model.setValueAt("Reservado: " + "\n" + horario.getJueves(), fila, 4);
                }
                if (horario.getViernes() != null && !horario.getViernes().isEmpty()) {
                    model.setValueAt("Reservado: " + horario.getViernes(), fila, 5);
                }
            } else {
                System.out.println("Hora no encontrada: " + hora);
            }
        }
    }

    private int obtenerFilaPorHora(String hora) {
        for (int i = 0; i < utcJTable1.getRowCount(); i++) {
            if (utcJTable1.getValueAt(i, 0).equals(hora)) {
                return i;
            }
        }
        return -1;
    }
//falta arreglar esta conf

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
                    String valorCelda = (String) utcJTable1.getValueAt(row, col);
                    String hora = (String) utcJTable1.getValueAt(row, 0); // Obteniendo la hora de la casilla seleccionada
                    JOptionPane.showMessageDialog(null, "Reservando celda en fila " + row + ", columna " + col + " con valor: " + valorCelda + " en hora: " + hora);

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
                    JOptionPane.showMessageDialog(null, "Reserva eliminada en celda en fila " + row + ", columna " + col);
                }
            }
        });

        popupMenu.add(reservarItem);
        popupMenu.add(modificarReservaItem);
        popupMenu.add(eliminarReservaItem);

        utcJTable1.addMouseListener(new MouseAdapter() {
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
                    if (selectedDate != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(selectedDate);
                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                        // Mapear el día de la semana a las columnas de la tabla (lunes -> 1, martes -> 2, ...)
                        int selectedColumn = dayOfWeek - 1;

                        // Actualizar el JDateChooser si la columna seleccionada es diferente al día seleccionado
                        if (col != selectedColumn) {
                            calendar.add(Calendar.DAY_OF_MONTH, col - selectedColumn);
                            jDateChooser2.setDate(calendar.getTime());
                        }

                        if (col != selectedColumn) {
                            // Si la columna seleccionada es posterior al día seleccionado, mostrar el menú emergente
                            if (col >= selectedColumn) {
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            }
                            // Si la columna seleccionada es anterior al día seleccionado, no hacer nada
                        }
                    }
                }
            }
        });
    }

    
private String ObtenerFecha(){
     SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
   return formatoFecha.format(this.jDateChooser2.getCalendar().getTime());
    
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Espacios Aulas/Laboratorios");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcmbBloques, 0, 148, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcmbLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
<<<<<<< HEAD
                        .addComponent(jcmbNumLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
=======
                        .addComponent(jcmbNumLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
>>>>>>> 357e77e15f69c2efb719d1a84a46c6560025fefc
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcmbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbNumLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(405, Short.MAX_VALUE))
        );
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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> jcmbBloques;
    private javax.swing.JComboBox<String> jcmbLabAulas;
    private javax.swing.JComboBox<String> jcmbNumLabAulas;
    // End of variables declaration//GEN-END:variables
}
