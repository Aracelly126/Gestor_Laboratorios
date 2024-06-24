/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

import Codes.Carrera;
import Codes.bd_materias;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Materia extends javax.swing.JPanel {

    /**
     * Creates new form Materia
     */
    private bd_materias materiaBD;
    private int selectedMateriaId = -1;

    /**
     * Creates new form Materia
     */
    public Materia() {
        initComponents();
        materiaBD = new bd_materias();
        cargarMaterias();
        jTableMateria.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });
    }

    private void cargarMaterias() {
    List<Codes.Materia> materias = materiaBD.obtenerMaterias();
    DefaultTableModel model = (DefaultTableModel) jTableMateria.getModel();
    model.setRowCount(0); // Limpia la tabla
    for (Codes. Materia materia : materias) {
        model.addRow(new Object[]{
            materia.getIdMateria(),
            materia.getNombre(),
            materia.getNumeroSemestre(),
            materia.getNombreCarrera()
        });
    }
}

    private void seleccionarTabla() {
        int selectedRow = jTableMateria.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTableMateria.getModel();
            selectedMateriaId = (int) model.getValueAt(selectedRow, 0);
            jTextFieldMateria.setText((String) model.getValueAt(selectedRow, 1));
            jTextFieldSemestre.setText(String.valueOf(model.getValueAt(selectedRow, 3)));
        }
    }

    private void accionCrear() {
        String nombreMateria = jTextFieldMateria.getText();
        String semestreText = jTextFieldSemestre.getText();
        Carrera carreraSeleccionada = (Carrera) jComboBoxCarrera.getSelectedItem(); // Obtiene la carrera seleccionada

        if (nombreMateria != null && !nombreMateria.isEmpty() && semestreText != null && !semestreText.isEmpty() && carreraSeleccionada != null) {
            try {
                int numeroSemestre = Integer.parseInt(semestreText);
                String nombreCarrera = carreraSeleccionada.getNombreCarrera(); // Obtiene el nombre de la carrera seleccionada

                boolean creada = materiaBD.crearMateria(nombreMateria, numeroSemestre, nombreCarrera);
                if (creada) {
                    cargarMaterias(); // Actualiza la tabla de materias
                    jTextFieldMateria.setText("");
                    jTextFieldSemestre.setText("");
                    jComboBoxCarrera.setSelectedIndex(-1); // Limpia la selección del ComboBox
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un semestre válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
        }
    }

    private void accionEditar() {
        if (selectedMateriaId != -1) {
            String nuevoNombre = jTextFieldMateria.getText();
            String semestreText = jTextFieldSemestre.getText();
            Carrera carreraSeleccionada = (Carrera) jComboBoxCarrera.getSelectedItem(); // Obtiene la carrera seleccionada

            if (nuevoNombre != null && !nuevoNombre.isEmpty() && semestreText != null && !semestreText.isEmpty() && carreraSeleccionada != null) {
                try {
                    int numeroSemestre = Integer.parseInt(semestreText);
                    String nombreCarrera = carreraSeleccionada.getNombreCarrera(); // Obtiene el nombre de la carrera seleccionada

                    boolean editado = materiaBD.editarMateria(selectedMateriaId, nuevoNombre, numeroSemestre, nombreCarrera);
                    if (editado) {
                        JOptionPane.showMessageDialog(null, "Materia actualizada con éxito."); // Mensaje de éxito primero
                        cargarMaterias();
                        limpiarCampos(); // Limpia los campos
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un semestre válido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            }
        }
    }

// Método para limpiar los campos de entrada y reiniciar selectedMateriaId
    private void limpiarCampos() {
        jTextFieldMateria.setText("");
        jTextFieldSemestre.setText("");
        jComboBoxCarrera.setSelectedIndex(-1); // Limpia la selección del ComboBox
        selectedMateriaId = -1;
    }

  private void seleccionarFila() {
    int selectedRow = jTableMateria.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) jTableMateria.getModel();
        selectedMateriaId = (int) model.getValueAt(selectedRow, 0); // Asegúrate de que el ID se obtiene correctamente
        jTextFieldMateria.setText((String) model.getValueAt(selectedRow, 1)); // Nombre de la Materia
        jTextFieldSemestre.setText(String.valueOf(model.getValueAt(selectedRow, 2))); // Número de Semestre
        
        String nombreCarrera = (String) model.getValueAt(selectedRow, 3); // Obtén el nombre de la Carrera
        for (int i = 0; i < jComboBoxCarrera.getItemCount(); i++) {
            Carrera carrera = (Carrera) jComboBoxCarrera.getItemAt(i);
            if (carrera.getNombreCarrera().equals(nombreCarrera)) {
                jComboBoxCarrera.setSelectedIndex(i); // Selecciona la carrera en el ComboBox
                break;
            }
        }
    } 
}

  private void accionEliminar() {
    if (selectedMateriaId != -1) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta materia?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = materiaBD.eliminarMateria(selectedMateriaId);
            if (eliminado) {
                cargarMaterias(); // Actualiza la tabla de materias
                limpiarCampos(); // Limpia los campos de entrada
                JOptionPane.showMessageDialog(null, "Materia eliminada con éxito.");
            }
        }
    } 
}

    private void cargarCarrerasEnComboBox() {
        List<Carrera> carreras = materiaBD.obtenerCarreras();
        jComboBoxCarrera.removeAllItems(); // Limpia el ComboBox

        for (int i = 0; i < carreras.size(); i++) {
            Carrera carrera = carreras.get(i); // Obtiene la carrera en la posición i
            jComboBoxCarrera.addItem(carrera); // Añade la carrera al ComboBox
        }
    }

    private void verificarYAccionEditar() {
        String nombreMateria = jTextFieldMateria.getText();
        String semestreText = jTextFieldSemestre.getText();
        Carrera carreraSeleccionada = (Carrera) jComboBoxCarrera.getSelectedItem(); // Obtiene la carrera seleccionada

        if (selectedMateriaId == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila de la tabla para editar.");
            return;
        }

        if (nombreMateria == null || nombreMateria.trim().isEmpty()
                || semestreText == null || semestreText.trim().isEmpty()
                || carreraSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos antes de editar.");
        } else {
            // Si todos los campos están completos, llama a la acción de editar
            accionEditar();
        }
    }
private boolean seleccionarFila1() {
    int selectedRow = jTableMateria.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) jTableMateria.getModel();
        selectedMateriaId = (int) model.getValueAt(selectedRow, 0); // Asegúrate de que el ID se obtiene correctamente
        jTextFieldMateria.setText((String) model.getValueAt(selectedRow, 1)); // Nombre de la Materia
        jTextFieldSemestre.setText(String.valueOf(model.getValueAt(selectedRow, 2))); // Número de Semestre
        
        String nombreCarrera = (String) model.getValueAt(selectedRow, 3); // Obtén el nombre de la Carrera
        for (int i = 0; i < jComboBoxCarrera.getItemCount(); i++) {
            Carrera carrera = (Carrera) jComboBoxCarrera.getItemAt(i);
            if (carrera.getNombreCarrera().equals(nombreCarrera)) {
                jComboBoxCarrera.setSelectedIndex(i); // Selecciona la carrera en el ComboBox
                break;
            }
        }
        return true; // Fila seleccionada exitosamente
    } else {
        return false; // No se seleccionó ninguna fila
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldMateria = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSemestre = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMateria = new javax.swing.JTable();
        jComboBoxCarrera = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("REGISTRO DE MATERIA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 400, 80));

        jLabel2.setText("Nombre de la Materia: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));
        jPanel1.add(jTextFieldMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 250, -1));

        jPanel2.setBackground(new java.awt.Color(173, 39, 46));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(170, 50));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ELIMINAR");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 110, 40));

        jPanel3.setBackground(new java.awt.Color(173, 39, 46));
        jPanel3.setPreferredSize(new java.awt.Dimension(165, 50));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("EDITAR");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 110, 40));

        jPanel4.setBackground(new java.awt.Color(173, 39, 46));
        jPanel4.setPreferredSize(new java.awt.Dimension(165, 50));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CREAR");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 110, 40));

        jLabel5.setText("Semestre:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 60, -1));
        jPanel1.add(jTextFieldSemestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 250, -1));

        jPanel5.setBackground(new java.awt.Color(173, 39, 46));
        jPanel5.setPreferredSize(new java.awt.Dimension(165, 50));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CANCELAR");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 110, 40));

        jLabel8.setText("Carrera:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));

        jTableMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID MATERIA", "MATERIA", "SEMESTRE", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableMateria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 570, 300));

        jComboBoxCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxCarreraMouseClicked(evt);
            }
        });
        jPanel1.add(jComboBoxCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 250, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        if (!seleccionarFila1()) {
        JOptionPane.showMessageDialog(null, "Por favor elija una fila de la tabla.");
        return; // No proceder si no se seleccionó una fila
    }
    accionEliminar();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        verificarYAccionEditar();

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        accionCrear();

    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        limpiarCampos();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jComboBoxCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxCarreraMouseClicked
        cargarCarrerasEnComboBox();
    }//GEN-LAST:event_jComboBoxCarreraMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Carrera> jComboBoxCarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMateria;
    private javax.swing.JTextField jTextFieldMateria;
    private javax.swing.JTextField jTextFieldSemestre;
    // End of variables declaration//GEN-END:variables
}
