    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Windows;

import java.awt.Color;
import java.awt.Desktop;
import java.net.URI;

/**
 *
 * @author User
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
 */
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        
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
        jlblBaner = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanelBloques = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelEspacios = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelHorarios = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanelCarrera = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanelProfesor = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanelMateria = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        panelShadow1 = new Utils.PanelShadow();
        jLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        pnlCerrarSesion = new Utils.PanelShadow();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblBaner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/banner.jpg"))); // NOI18N
        jPanel1.add(jlblBaner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 160));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 160));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelBloques.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBloques.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelBloquesMouseMoved(evt);
            }
        });
        jPanelBloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBloquesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBloquesMouseExited(evt);
            }
        });
        jPanelBloques.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edificios.png"))); // NOI18N
        jPanelBloques.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("BLOQUES");
        jPanelBloques.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 40));

        jPanel2.add(jPanelBloques, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 240, 60));

        jPanelEspacios.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEspacios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelEspaciosMouseMoved(evt);
            }
        });
        jPanelEspacios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelEspaciosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelEspaciosMouseExited(evt);
            }
        });
        jPanelEspacios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/aula.png"))); // NOI18N
        jPanelEspacios.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("ESPACIOS");
        jPanelEspacios.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 100, 40));

        jPanel2.add(jPanelEspacios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 240, 60));

        jPanelHorarios.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHorarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelHorariosMouseMoved(evt);
            }
        });
        jPanelHorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelHorariosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelHorariosMouseExited(evt);
            }
        });
        jPanelHorarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/calendario.png"))); // NOI18N
        jPanelHorarios.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("RESERVAS");
        jPanelHorarios.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, 40));

        jPanel2.add(jPanelHorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 240, 60));

        jPanelCarrera.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCarrera.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelCarreraMouseMoved(evt);
            }
        });
        jPanelCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCarreraMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCarreraMouseExited(evt);
            }
        });
        jPanelCarrera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cambiar.png"))); // NOI18N
        jPanelCarrera.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("CARRERA");
        jPanelCarrera.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 120, 40));

        jPanel2.add(jPanelCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 250, 60));

        jPanelProfesor.setBackground(new java.awt.Color(255, 255, 255));
        jPanelProfesor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelProfesorMouseMoved(evt);
            }
        });
        jPanelProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelProfesorMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelProfesorMouseExited(evt);
            }
        });
        jPanelProfesor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/profesor.png"))); // NOI18N
        jLabel15.setText("jLabel15");
        jPanelProfesor.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("PROFESOR");
        jPanelProfesor.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 120, 40));

        jPanel2.add(jPanelProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 60));

        jPanelMateria.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMateria.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelMateriaMouseMoved(evt);
            }
        });
        jPanelMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelMateriaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelMateriaMouseExited(evt);
            }
        });
        jPanelMateria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/libro.png"))); // NOI18N
        jPanelMateria.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("MATERIA");
        jPanelMateria.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 120, 40));

        jPanel2.add(jPanelMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 250, 60));

        panelShadow1.setBackground(new java.awt.Color(173, 39, 46));
        panelShadow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelShadow1MouseClicked(evt);
            }
        });
        panelShadow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Enviar Solicitud");
        panelShadow1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/documents.png"))); // NOI18N
        panelShadow1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, 32));

        jPanel2.add(panelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 190, 60));

        pnlCerrarSesion.setBackground(new java.awt.Color(204, 204, 204));
        pnlCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCerrarSesionMouseClicked(evt);
            }
        });
        pnlCerrarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Cerrar Sesión");
        pnlCerrarSesion.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1, -1, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logout.png"))); // NOI18N
        pnlCerrarSesion.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 40));

        jPanel2.add(pnlCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 190, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 260, 630));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("BLOQUES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(514, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("ESPACIOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(539, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("RESERVAS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(552, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("CARRERA");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(522, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setText("PROFESOR");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(503, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("MATERIA");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(545, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", jPanel10);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 710, 630));
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelBloquesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBloquesMouseClicked
          jTabbedPane1.setSelectedIndex(0);
        Bloques bloque = new Bloques();
        bloque.setSize(710,596);
        jPanel4.removeAll();
        jPanel4.add(bloque);
        bloque.setVisible(true);
        jPanel4.repaint();
        jPanel4.revalidate();  
    }//GEN-LAST:event_jPanelBloquesMouseClicked

    private void jPanelBloquesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBloquesMouseMoved
        jPanelBloques.setBackground(new Color(173, 39, 46));
        
    }//GEN-LAST:event_jPanelBloquesMouseMoved

    private void jPanelBloquesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBloquesMouseExited
        jPanelBloques.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jPanelBloquesMouseExited

    private void jPanelEspaciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEspaciosMouseClicked
        jTabbedPane1.setSelectedIndex(1);
        Espacios esp = new Espacios();
        esp.setSize(710, 596);
        jPanel5.removeAll();
        jPanel5.add(esp);
        esp.setVisible(true);
        jPanel5.repaint();
        jPanel5.revalidate();
        
    }//GEN-LAST:event_jPanelEspaciosMouseClicked

    private void jPanelEspaciosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEspaciosMouseMoved

        jPanelEspacios.setBackground(new Color(173, 39, 46));
    }//GEN-LAST:event_jPanelEspaciosMouseMoved

    private void jPanelEspaciosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEspaciosMouseExited
        jPanelEspacios.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jPanelEspaciosMouseExited

    private void jPanelHorariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelHorariosMouseClicked
        jTabbedPane1.setSelectedIndex(2);
        HorariosFISEI horario = new  HorariosFISEI();
        horario.setSize(710, 596);
        jPanel6.removeAll();
        jPanel6.add(horario);
        horario.setVisible(true);
        jPanel6.repaint();
        jPanel6.revalidate();

    }//GEN-LAST:event_jPanelHorariosMouseClicked

    private void jPanelHorariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelHorariosMouseMoved
        jPanelHorarios.setBackground(new Color(173, 39, 46));
    }//GEN-LAST:event_jPanelHorariosMouseMoved

    private void jPanelHorariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelHorariosMouseExited
        jPanelHorarios.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jPanelHorariosMouseExited

    private void jPanelCarreraMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCarreraMouseMoved
        jPanelCarrera.setBackground(new Color(173, 39, 46));
    }//GEN-LAST:event_jPanelCarreraMouseMoved

    private void jPanelCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCarreraMouseClicked
        jTabbedPane1.setSelectedIndex(3);
        Carreras carrera = new Carreras();
        carrera.setSize(710, 596);
        jPanel8.removeAll();
        jPanel8.add(carrera);
        carrera.setVisible(true);
        jPanel8.repaint();
        jPanel8.revalidate();
    }//GEN-LAST:event_jPanelCarreraMouseClicked

    private void jPanelCarreraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCarreraMouseExited
        jPanelCarrera.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jPanelCarreraMouseExited

    private void jPanelProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProfesorMouseClicked
        jTabbedPane1.setSelectedIndex(4);
        Profesores esp = new Profesores();
        esp.setSize(710, 596);
        jPanel9.removeAll();
        jPanel9.add(esp);
        esp.setVisible(true);
        jPanel9.repaint();
        jPanel9.revalidate();
    }//GEN-LAST:event_jPanelProfesorMouseClicked

    private void jPanelProfesorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProfesorMouseMoved
        jPanelProfesor.setBackground(new Color(173, 39, 46));
    }//GEN-LAST:event_jPanelProfesorMouseMoved

    private void jPanelProfesorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProfesorMouseExited
        jPanelProfesor.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jPanelProfesorMouseExited

    private void jPanelMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMateriaMouseClicked
        jTabbedPane1.setSelectedIndex(5);
         Materia materias = new Materia();
        materias.setSize(710,596);
        jPanel10.removeAll();
        jPanel10.add(materias);
        materias.setVisible(true);
        jPanel10.repaint();
        jPanel10.revalidate(); 
    }//GEN-LAST:event_jPanelMateriaMouseClicked

    private void jPanelMateriaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMateriaMouseMoved
        jPanelMateria.setBackground(new Color(173, 39, 46));
    }//GEN-LAST:event_jPanelMateriaMouseMoved

    private void jPanelMateriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMateriaMouseExited
        jPanelMateria.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jPanelMateriaMouseExited

    private void pnlEliminarBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlEliminarBloqueMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pnlEliminarBloqueMouseClicked

    private void panelShadow1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelShadow1MouseClicked
        // TODO add your handling code here:
        
        try {
            Desktop.getDesktop().browse(new URI("https://paginagigapro2.es/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_panelShadow1MouseClicked
    Login log = new Login();
    private void pnlCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCerrarSesionMouseClicked
        setVisible(false);
        dispose();
    
        log.setVisible(true);
    }//GEN-LAST:event_pnlCerrarSesionMouseClicked
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBloques;
    private javax.swing.JPanel jPanelCarrera;
    private javax.swing.JPanel jPanelEspacios;
    private javax.swing.JPanel jPanelHorarios;
    private javax.swing.JPanel jPanelMateria;
    private javax.swing.JPanel jPanelProfesor;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jlblBaner;
    private Utils.PanelShadow panelShadow1;
    private Utils.PanelShadow pnlCerrarSesion;
    // End of variables declaration//GEN-END:variables
}
