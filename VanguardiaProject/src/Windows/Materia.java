/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

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
    }

     private void cargarMaterias() {
        List<Codes.Materia> materias = materiaBD.obtenerMaterias();
        DefaultTableModel model = (DefaultTableModel) jTableMateria.getModel();
        model.setRowCount(0); 
        for (Codes.Materia materia : materias) {
            model.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), materia.getSemestreId()});
        }
    }

    public void accionCrear() {
        String nombreMateria = jTextFieldMateria.getText();
        String semestreText = jTextFieldSemestre.getText();
        if (!nombreMateria.isEmpty() && !semestreText.isEmpty()) {
            try {
                int semestreId = Integer.parseInt(semestreText);
                boolean creado = materiaBD.crearMateria(nombreMateria, semestreId);
                if (creado) {
                    cargarMaterias();
                    jTextFieldMateria.setText("");
                    jTextFieldSemestre.setText("");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un semestre válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre para la materia y un semestre.");
        }
    }

    public void accionEditar() {
        String nuevoNombre = jTextFieldMateria.getText();
        String semestreText = jTextFieldSemestre.getText();
        if (selectedMateriaId != -1 && !nuevoNombre.isEmpty() && !semestreText.isEmpty()) {
            try {
                int semestreId = Integer.parseInt(semestreText);
                boolean editado = materiaBD.editarMateria(selectedMateriaId, nuevoNombre, semestreId);
                if (editado) {
                    cargarMaterias();
                    jTextFieldMateria.setText("");
                    jTextFieldSemestre.setText("");
                    selectedMateriaId = -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un semestre válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una materia de la tabla y edite su nombre y semestre.");
        }
    }

    public void accionEliminar() {
        if (selectedMateriaId != -1) {
            boolean eliminado = materiaBD.eliminarMateria(selectedMateriaId);
            if (eliminado) {
                cargarMaterias();
                jTextFieldMateria.setText("");
                jTextFieldSemestre.setText("");
                selectedMateriaId = -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una materia de la tabla para eliminar.");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMateria = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSemestre = new javax.swing.JTextField();

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
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

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
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 14, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

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
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        jTableMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { null, null, null},
                { null, null, null},
                { null, null, null},
                { null, null, null}
            },
            new String [] {
                "Id Materia", "Nombre de la Materia", "Semestre"
            }
        ));
        jTableMateria.setMinimumSize(new java.awt.Dimension(30, 80));
        jTableMateria.setPreferredSize(new java.awt.Dimension(150, 80));
        jTableMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMateriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMateria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 570, 260));

        jLabel5.setText("Semestre:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 60, -1));
        jPanel1.add(jTextFieldSemestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 250, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        accionEliminar();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        accionEliminar();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        accionCrear();

    }//GEN-LAST:event_jPanel4MouseClicked

    private void jTableMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMateriaMouseClicked
        seleccionarTabla();    }//GEN-LAST:event_jTableMateriaMouseClicked
    private void seleccionarTabla() {
        int selectedRow = jTableMateria.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTableMateria.getModel();
            selectedMateriaId = (int) model.getValueAt(selectedRow, 0);
            jTextFieldMateria.setText((String) model.getValueAt(selectedRow, 1));
            jTextFieldSemestre.setText(String.valueOf(model.getValueAt(selectedRow, 2)));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMateria;
    private javax.swing.JTextField jTextFieldMateria;
    private javax.swing.JTextField jTextFieldSemestre;
    // End of variables declaration//GEN-END:variables
}
