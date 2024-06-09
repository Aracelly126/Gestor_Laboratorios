/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

import Codes.Bloques;
import Codes.FiltradorAulas_Lab;
import Codes.Horario;
import Codes.Horarios;
import Codes.Lab_Aulas;
import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class HorariosFISEI extends javax.swing.JPanel {

    /**
     * Creates new form HorariosFISEI
     */
    public HorariosFISEI() {
        initComponents();
        actualizarBloques();
        filtradoUno();
        configureJDateChooser();
        toggleComboBoxes(false);
        Menu();
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
    public void Menu(){
        JPopupMenu pop = new JPopupMenu();
        JMenuItem menu = new JMenuItem("Reservar");
        pop.add(menu);
        utcJTable1.setComponentPopupMenu(pop);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        utcJTable1 = new ComponentesPropios.utcJTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("BLOQUES");

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

        jDateChooser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser2MouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(utcJTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcmbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jcmbLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jcmbNumLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcmbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbNumLabAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
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
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcmbBloques;
    private javax.swing.JComboBox<String> jcmbLabAulas;
    private javax.swing.JComboBox<String> jcmbNumLabAulas;
    private ComponentesPropios.utcJTable utcJTable1;
    // End of variables declaration//GEN-END:variables
}
