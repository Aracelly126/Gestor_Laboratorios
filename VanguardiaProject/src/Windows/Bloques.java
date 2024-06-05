/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Windows;

import Codes.bd_bloques;
import Codes.Bloque;
import Utils.Encript;
import Utils.ManejoComp;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import Utils.Conex;

public class Bloques extends javax.swing.JPanel {

    private bd_bloques bloqueBD;
    private ArrayList<String> bloquesList;
    private int selectedBloqueId = -1;

    public Bloques() {
        initComponents();
        bloqueBD = new bd_bloques();
        bloquesList = new ArrayList<>();
        cargarBloques();

    }

    private void cargarBloques() {
        List<Bloque> bloques = bloqueBD.obtenerBloques();
        DefaultTableModel model = (DefaultTableModel) tblBloques.getModel();
        model.setRowCount(0);
        for (Bloque bloque : bloques) {
            model.addRow(new Object[]{bloque.getId(), bloque.getNombre()});

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBloques = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBloques = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        pnlEliminarBloque = new Utils.PanelShadow();
        jLabel2 = new javax.swing.JLabel();
        pnlEditarBloque = new Utils.PanelShadow();
        jLabel3 = new javax.swing.JLabel();
        pnlCrearBloque = new Utils.PanelShadow();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBloques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Numero de Bloque", "Nombre de bloque"
            }
        ));
        tblBloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBloquesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBloques);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 630, 303));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre del Bloque:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 140, 22));

        txtBloques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBloquesActionPerformed(evt);
            }
        });
        add(txtBloques, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 197, 30));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Registro de Bloques");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 372, 46));

        pnlEliminarBloque.setBackground(new java.awt.Color(173, 39, 46));
        pnlEliminarBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlEliminarBloqueMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ELIMINAR");

        javax.swing.GroupLayout pnlEliminarBloqueLayout = new javax.swing.GroupLayout(pnlEliminarBloque);
        pnlEliminarBloque.setLayout(pnlEliminarBloqueLayout);
        pnlEliminarBloqueLayout.setHorizontalGroup(
            pnlEliminarBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEliminarBloqueLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(44, 44, 44))
        );
        pnlEliminarBloqueLayout.setVerticalGroup(
            pnlEliminarBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEliminarBloqueLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(14, 14, 14))
        );

        add(pnlEliminarBloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        pnlEditarBloque.setBackground(new java.awt.Color(173, 39, 46));
        pnlEditarBloque.setPreferredSize(new java.awt.Dimension(170, 50));
        pnlEditarBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlEditarBloqueMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("EDITAR");

        javax.swing.GroupLayout pnlEditarBloqueLayout = new javax.swing.GroupLayout(pnlEditarBloque);
        pnlEditarBloque.setLayout(pnlEditarBloqueLayout);
        pnlEditarBloqueLayout.setHorizontalGroup(
            pnlEditarBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarBloqueLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel3)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        pnlEditarBloqueLayout.setVerticalGroup(
            pnlEditarBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarBloqueLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(pnlEditarBloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        pnlCrearBloque.setBackground(new java.awt.Color(173, 39, 46));
        pnlCrearBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCrearBloqueMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CREAR");

        javax.swing.GroupLayout pnlCrearBloqueLayout = new javax.swing.GroupLayout(pnlCrearBloque);
        pnlCrearBloque.setLayout(pnlCrearBloqueLayout);
        pnlCrearBloqueLayout.setHorizontalGroup(
            pnlCrearBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearBloqueLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(65, 65, 65))
        );
        pnlCrearBloqueLayout.setVerticalGroup(
            pnlCrearBloqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrearBloqueLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(16, 16, 16))
        );

        add(pnlCrearBloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBloquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBloquesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBloquesActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void pnlCrearBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCrearBloqueMouseClicked
        accionCrear();
    }//GEN-LAST:event_pnlCrearBloqueMouseClicked
    public void accionCrear() {
        String nombreBloque = txtBloques.getText();
        if (!nombreBloque.isEmpty()) {
            boolean creado = bloqueBD.crearBloque(nombreBloque);
            if (creado) {
                cargarBloques();
                txtBloques.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre para el bloque.");
            
        }
    }
    private void pnlEditarBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEditarBloqueMouseClicked
        editarBloque();
    }//GEN-LAST:event_pnlEditarBloqueMouseClicked
    public void editarBloque() {
        if (selectedBloqueId != -1) {
            String nombreNuevo = txtBloques.getText();
            if (nombreNuevo != null && !nombreNuevo.isEmpty()) {
                boolean editado = bloqueBD.editarBloque(selectedBloqueId, nombreNuevo);
                if (editado) {
                    JOptionPane.showMessageDialog(this, "Bloque editado correctamente.");
                    cargarBloques();
                    selectedBloqueId = -1; 
                    txtBloques.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al editar el bloque.");
                    txtBloques.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido.");
                txtBloques.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un bloque para editar.");
            txtBloques.setText("");
        }

    }

    private void pnlEliminarBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarBloqueMouseClicked
        eliminarBloque();
    }//GEN-LAST:event_pnlEliminarBloqueMouseClicked
    public void eliminarBloque() {
        int filaSelecionada = tblBloques.getSelectedRow();
        if (filaSelecionada != 1) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que  quieres eliminar este bloque?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int idBloque = Integer.parseInt(tblBloques.getValueAt(filaSelecionada, 0).toString());
                if (bloqueBD.eliminarBloque(idBloque)) {
                    cargarBloques();
                    txtBloques.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el bloque");
                    txtBloques.setText("");
                }
            }
        }
    }
    private void tblBloquesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBloquesMouseClicked
        selecionarTabla();
    }//GEN-LAST:event_tblBloquesMouseClicked
    public void selecionarTabla() {
        int selectedRow = tblBloques.getSelectedRow();
        if (selectedRow != -1) {
            selectedBloqueId = (int) tblBloques.getValueAt(selectedRow, 0);
            String nombreBloque = (String) tblBloques.getValueAt(selectedRow, 1);
            txtBloques.setText(nombreBloque);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private Utils.PanelShadow pnlCrearBloque;
    private Utils.PanelShadow pnlEditarBloque;
    private Utils.PanelShadow pnlEliminarBloque;
    private javax.swing.JTable tblBloques;
    private javax.swing.JTextField txtBloques;
    // End of variables declaration//GEN-END:variables
}
