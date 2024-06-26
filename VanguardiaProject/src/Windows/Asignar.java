/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

import Codes.FiltradorAulas_Lab;
import Codes.Horario;
import Codes.Bloques;
import Codes.Horarios;

import Codes.Lab_Aulas;
import CodesBD.CrudReservas;
import CodesBD.Reserva;
import Utils.crud_asignacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Asignar extends javax.swing.JPanel {

    crud_asignacion ca = new crud_asignacion();
    private JPopupMenu popupMenu;

    /**
     * Creates new form Asignar
     */
    public Asignar() {
        initComponents();
        ca.cargarProfesor(jComboBoxProfesor);
        ca.cargarMaterias(jComboBoxMateria);
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

    // Comprobar si todos los campos están seleccionados antes de llamar a insertar
    if (jcmbNumLabAulas.getItemCount() > 0 && jComboBoxProfesor.getSelectedItem() != null && jComboBoxMateria.getSelectedItem() != null) {
        insertar();
    }
}
 public void configurarTabla() {
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Permitir la edición de todas las celdas
            return true;
        }
    };
    utcJTable1.setModel(model);
}


 public void insertar() {
    Object bloqueSeleccionado = jcmbBloques.getSelectedItem();
    Object tipoEspacioSeleccionado = jcmbLabAulas.getSelectedItem();
    Object laboAulSeleccionado = jcmbNumLabAulas.getSelectedItem();
    Object profesorSeleccionado = jComboBoxProfesor.getSelectedItem();
    Object materiaSeleccionada = jComboBoxMateria.getSelectedItem();

    boolean camposCompletos = bloqueSeleccionado != null &&
                              tipoEspacioSeleccionado != null &&
                              laboAulSeleccionado != null &&
                              profesorSeleccionado != null &&
                              materiaSeleccionada != null;

    if (camposCompletos) {
        String nombreBloque = bloqueSeleccionado.toString();
        String tipoEspacio = tipoEspacioSeleccionado.toString();
        String laboAul = laboAulSeleccionado.toString();
        String profesor = profesorSeleccionado.toString();
        String materia = materiaSeleccionada.toString();

        Horarios horarios = new Horarios();
        List<Horario> horariosList = horarios.obtenerAulasPorBloqueYSala(nombreBloque, tipoEspacio, laboAul);

        DefaultTableModel model = (DefaultTableModel) utcJTable1.getModel();

        for (Horario horario : horariosList) {
            String hora = horario.getHora();
            int fila = obtenerFilaPorHora(hora);
            if (fila >= 0) {
                if (horario.getLunes() != null && !horario.getLunes().isEmpty()) {
                    String valorCelda = (String) model.getValueAt(fila, 1);
                    if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:")) {
                        model.setValueAt("Clase: " + horario.getLunes(), fila, 1);
                    }
                }
                if (horario.getMartes() != null && !horario.getMartes().isEmpty()) {
                    String valorCelda = (String) model.getValueAt(fila, 2);
                    if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:")) {
                        model.setValueAt("Clase: " + horario.getMartes(), fila, 2);
                    }
                }
                if (horario.getMiercoles() != null && !horario.getMiercoles().isEmpty()) {
                    String valorCelda = (String) model.getValueAt(fila, 3);
                    if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:")) {
                        model.setValueAt("Clase: " + horario.getMiercoles(), fila, 3);
                    }
                }
                if (horario.getJueves() != null && !horario.getJueves().isEmpty()) {
                    String valorCelda = (String) model.getValueAt(fila, 4);
                    if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:")) {
                        model.setValueAt("Clase: " + horario.getJueves(), fila, 4);
                    }
                }
                if (horario.getViernes() != null && !horario.getViernes().isEmpty()) {
                    String valorCelda = (String) model.getValueAt(fila, 5);
                    if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:")) {
                        model.setValueAt("Clase: " + horario.getViernes(), fila, 5);
                    }
                }
            } else {
                System.out.println("Hora no encontrada: " + hora);
            }
        }

        CrudReservas crudReservas = new CrudReservas();
        List<Reserva> reservasList = crudReservas.obtenerReservasPorEspacio(laboAul);

        for (Reserva reserva : reservasList) {
            String hora = reserva.getHora();
            int fila = obtenerFilaPorHora(hora);
            int columna = obtenerColumnaPorFecha(reserva.getFecha());
            if (fila >= 0 && columna > 0) {
                model.setValueAt("Préstamo: " + reserva.getObservacion(), fila, columna);
            }
        }

        // Añadir lógica para asignar nueva clase con materia y profesor
        int filaNueva = obtenerFilaPorHora((String) bloqueSeleccionado);
        if (filaNueva >= 0) {
            model.setValueAt("Clase: " + materia + "\nProfesor: " + profesor, filaNueva, obtenerColumnaPorFecha((Date) tipoEspacioSeleccionado));
        }

        utcJTable1.revalidate();
        utcJTable1.repaint();
    } 
}



   
   
   
private int obtenerFilaPorHora(String hora) {
    DefaultTableModel model = (DefaultTableModel) utcJTable1.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        String horaTabla = (String) model.getValueAt(i, 0);
        if (horaTabla.equals(hora)) {
            return i;
        }
    }
    return -1; // Retorna -1 si la hora no se encuentra en ninguna fila
}
private int obtenerColumnaPorFecha(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    int diaSemana = cal.get(Calendar.DAY_OF_WEEK);

    // Mapear de Calendar.DAY_OF_WEEK a columna en la tabla
    // Domingo (Calendar.SUNDAY) es 1, lunes (Calendar.MONDAY) es 2, ...
    // Pero en tu tabla, domingo probablemente no se usa y lunes debería ser la columna 1
    if (diaSemana == Calendar.SUNDAY) {
        return -1; // Si es domingo, ignora ya que tu tabla probablemente no usa domingo
    }
    return diaSemana; // De Calendar.SUNDAY (1) a Calendar.SATURDAY (7), retorna el día de la semana
}


private void configurePopupMenu() {
    popupMenu = new JPopupMenu();
    JMenuItem asignarItem = new JMenuItem("Asignar");
    JMenuItem modificarReservaItem = new JMenuItem("Modificar");
    JMenuItem eliminarReservaItem = new JMenuItem("Eliminar");

    // Añadir las opciones "Seleccionar materia" y "Seleccionar profesor" a los JComboBox
    jComboBoxMateria.insertItemAt("Seleccionar materia", 0);
    jComboBoxMateria.setSelectedIndex(0);

    jComboBoxProfesor.insertItemAt("Seleccionar profesor", 0);
    jComboBoxProfesor.setSelectedIndex(0);

    asignarItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = utcJTable1.getSelectedRow();
            int col = utcJTable1.getSelectedColumn();
            if (row >= 0 && col >= 0) {
                String valorCelda = (String) utcJTable1.getValueAt(row, col);
                if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:")) {
                    // Mostrar los JComboBox con las opciones de "Seleccionar"
                    jComboBoxMateria.setSelectedIndex(0);
                    jComboBoxProfesor.setSelectedIndex(0);

                    int result = JOptionPane.showConfirmDialog(null, new Object[]{"Materia:", jComboBoxMateria, "Profesor:", jComboBoxProfesor}, "Asignar Reserva", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String materia = (String) jComboBoxMateria.getSelectedItem();
                        String profesor = (String) jComboBoxProfesor.getSelectedItem();
                        if (!materia.equals("Seleccionar materia") && !profesor.equals("Seleccionar profesor")) {
                            utcJTable1.setValueAt("Asignado: Clase: " + materia + "\nProfesor: " + profesor, row, col);
                            JOptionPane.showMessageDialog(null, "Reserva realizada con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe seleccionar una materia y un profesor.");
                        }
                    }
                } else if (valorCelda.startsWith("Clase:")) {
                    JOptionPane.showMessageDialog(null, "No se puede asignar una nueva reserva en esta celda porque ya contiene una clase asignada.");
                }
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
                if (valorCelda != null && valorCelda.startsWith("Asignado:")) {
                    // Obtener los valores actuales de materia y profesor
                    String[] partes = valorCelda.split("\n");
                    String materiaActual = partes[0].replace("Asignado: Clase: ", "").trim();
                    String profesorActual = partes[1].replace("Profesor: ", "").trim();

                    // Mostrar los JComboBox con los valores actuales seleccionados
                    jComboBoxMateria.setSelectedItem(materiaActual);
                    jComboBoxProfesor.setSelectedItem(profesorActual);

                    int result = JOptionPane.showConfirmDialog(null, new Object[]{"Materia:", jComboBoxMateria, "Profesor:", jComboBoxProfesor}, "Modificar Reserva", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String nuevaMateria = (String) jComboBoxMateria.getSelectedItem();
                        String nuevoProfesor = (String) jComboBoxProfesor.getSelectedItem();
                        if (!nuevaMateria.equals("Seleccionar materia") && !nuevoProfesor.equals("Seleccionar profesor")) {
                            utcJTable1.setValueAt("Asignado: Clase: " + nuevaMateria + "\nProfesor: " + nuevoProfesor, row, col);
                            JOptionPane.showMessageDialog(null, "Reserva modificada con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe seleccionar una materia y un profesor.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede modificar esta celda.");
                }
            }
        }
    });

    eliminarReservaItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = utcJTable1.getSelectedRow();
            int col = utcJTable1.getSelectedColumn();
            if (row >= 0 && col >= 0) {
                String valorCelda = (String) utcJTable1.getValueAt(row, col);
                if (valorCelda != null && valorCelda.startsWith("Asignado:")) {
                    utcJTable1.setValueAt("", row, col); // Limpiar la celda
                    JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar esta celda.");
                }
            }
        }
    });

    popupMenu.add(asignarItem);
    popupMenu.add(modificarReservaItem);
    popupMenu.add(eliminarReservaItem);

    utcJTable1.setComponentPopupMenu(popupMenu);

    utcJTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        private void showPopup(MouseEvent e) {
            int row = utcJTable1.rowAtPoint(e.getPoint());
            int col = utcJTable1.columnAtPoint(e.getPoint());
            if (row >= 0 && col >= 0) {
                utcJTable1.setRowSelectionInterval(row, row);
                utcJTable1.setColumnSelectionInterval(col, col);
                String valorCelda = (String) utcJTable1.getValueAt(row, col);
                if (valorCelda == null || valorCelda.isEmpty() || valorCelda.startsWith("Préstamo:") || valorCelda.startsWith("Asignado:")) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede realizar ninguna acción en esta celda porque ya está reservada.");
                }
            }
        }
    });
}







    public boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public String ObtenerFecha() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(this.jDateChooser2.getCalendar().getTime());
    }

    public String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    public boolean esHoraValida(String horaSeleccionada, String horaActual) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        utcJTable1 = new ComponentesPropios.utcJTable();
        jcmbBloques = new javax.swing.JComboBox<>();
        jcmbLabAulas = new javax.swing.JComboBox<>();
        jcmbNumLabAulas = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBoxMateria = new javax.swing.JComboBox<>();
        jComboBoxProfesor = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("ASIGNAR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 160, 50));

        jScrollPane1.setViewportView(utcJTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 660, 480));

        jcmbBloques.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jcmbBloques, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 180, -1));

        jcmbLabAulas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
        jPanel1.add(jcmbLabAulas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 180, -1));

        jcmbNumLabAulas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbNumLabAulas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcmbNumLabAulasMouseClicked(evt);
            }
        });
        jPanel1.add(jcmbNumLabAulas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 180, -1));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 180, -1));

        jComboBoxMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBoxMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 180, -1));

        jComboBoxProfesor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProfesorActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 180, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbLabAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbLabAulasMouseClicked
        filtradoUno();
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbLabAulasMouseClicked

    private void jcmbNumLabAulasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbNumLabAulasMouseClicked
        filtroDos();
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbNumLabAulasMouseClicked

    private void jcmbLabAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbLabAulasActionPerformed
        insertar();
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbLabAulasActionPerformed

    private void jComboBoxProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProfesorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProfesorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxMateria;
    private javax.swing.JComboBox<String> jComboBoxProfesor;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcmbBloques;
    private javax.swing.JComboBox<String> jcmbLabAulas;
    private javax.swing.JComboBox<String> jcmbNumLabAulas;
    private ComponentesPropios.utcJTable utcJTable1;
    // End of variables declaration//GEN-END:variables

}
