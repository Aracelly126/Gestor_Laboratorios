/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

import Codes.bd_carreras;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arita
 */
public class Carreras extends javax.swing.JPanel {

  private bd_carreras carreraBD;
    private ArrayList<String> carrerasList;
    private int selectedCarreraId = -1;
    
  /**
     * Creates new form Carreras
     */
    public Carreras() {
        initComponents();
        carreraBD = new bd_carreras();
        carrerasList = new ArrayList<>();
        cargarCarreras();

    }
  
    private void cargarCarreras() {
        List<Codes.Carreras> carreras = carreraBD.obtenerCarreras();
        DefaultTableModel model = (DefaultTableModel) tblCarreras.getModel();
        model.setRowCount(0);
        for (Codes.Carreras carrera : carreras) {
            model.addRow(new Object[]{carrera.getId(), carrera.getNombre()});
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

        pnlEliminar = new Utils.PanelShadow();
        jLabel3 = new javax.swing.JLabel();
        pblEditar = new Utils.PanelShadow();
        jLabel2 = new javax.swing.JLabel();
        pnlCrear = new Utils.PanelShadow();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCarreras = new javax.swing.JTable();
        lblCarreras = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCarrera = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlEliminar.setBackground(new java.awt.Color(173, 39, 46));
        pnlEliminar.setForeground(new java.awt.Color(173, 39, 46));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ELIMINAR");

        javax.swing.GroupLayout pnlEliminarLayout = new javax.swing.GroupLayout(pnlEliminar);
        pnlEliminar.setLayout(pnlEliminarLayout);
        pnlEliminarLayout.setHorizontalGroup(
            pnlEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEliminarLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        pnlEliminarLayout.setVerticalGroup(
            pnlEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEliminarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        add(pnlEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 190, 50));

        pblEditar.setBackground(new java.awt.Color(173, 39, 46));
        pblEditar.setForeground(new java.awt.Color(173, 39, 46));
        pblEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pblEditarMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("EDITAR");

        javax.swing.GroupLayout pblEditarLayout = new javax.swing.GroupLayout(pblEditar);
        pblEditar.setLayout(pblEditarLayout);
        pblEditarLayout.setHorizontalGroup(
            pblEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblEditarLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        pblEditarLayout.setVerticalGroup(
            pblEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblEditarLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(16, 16, 16))
        );

        add(pblEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 190, 52));

        pnlCrear.setBackground(new java.awt.Color(173, 39, 46));
        pnlCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCrearMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CREAR");

        javax.swing.GroupLayout pnlCrearLayout = new javax.swing.GroupLayout(pnlCrear);
        pnlCrear.setLayout(pnlCrearLayout);
        pnlCrearLayout.setHorizontalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(66, 66, 66))
        );
        pnlCrearLayout.setVerticalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(pnlCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 141, 190, 50));

        tblCarreras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Numero de Carrera", "Nombre de la Carrera"
            }
        ));
        jScrollPane1.setViewportView(tblCarreras);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 620, 341));

        lblCarreras.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCarreras.setText("Registro de carreras");
        add(lblCarreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre de la carrera:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));
        add(txtCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 210, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void pnlCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCrearMouseClicked
       accionCrear();
    }//GEN-LAST:event_pnlCrearMouseClicked

    private void pblEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pblEditarMouseClicked
        editarBloque();
    }//GEN-LAST:event_pblEditarMouseClicked

    public void accionCrear() {
        String nombreCarrera = txtCarrera.getText();
        if (!nombreCarrera.isEmpty()) {
            boolean creado = carreraBD.crearCarrera(nombreCarrera);
            if (creado) {
                cargarCarreras();
                txtCarrera.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre para la carrera.");

        }
    }
    public void editarBloque() {
        if (selectedCarreraId != -1) {
            String nombreNuevo = txtCarrera.getText();
            if (nombreNuevo != null && !nombreNuevo.isEmpty()) {
                boolean editado = carreraBD.editarCarrera(selectedCarreraId, nombreNuevo);
                if (editado) {
                    JOptionPane.showMessageDialog(this, "Carrera editada correctamente.");
                    cargarCarreras();
                    selectedCarreraId = -1; 
                    txtCarrera.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al editar la carrera.");
                    txtCarrera.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido.");
                txtCarrera.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una carrera para editar.");
            txtCarrera.setText("");
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCarreras;
    private Utils.PanelShadow pblEditar;
    private Utils.PanelShadow pnlCrear;
    private Utils.PanelShadow pnlEliminar;
    private javax.swing.JTable tblCarreras;
    private javax.swing.JTextField txtCarrera;
    // End of variables declaration//GEN-END:variables
}
