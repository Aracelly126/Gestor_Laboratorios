package Windows;

import Codes.bd_login;
import Utils.Encript;
import Utils.ManejoComp;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Login extends javax.swing.JFrame {

    private String correo = "";
    private String clave = "";
    
    public Login() {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        
        ManejoComp.crearlabel(this.lbl_header, "src\\IMG\\banner-fisei-uta.png");
        Image img = Toolkit.getDefaultToolkit().getImage("src\\IMG\\favicon.png");
        this.setIconImage(img);
        ManejoComp.crearVerPassword(this.txt_clave, this.btn_verClave);
        ManejoComp.crearlabel(this.lbl_usuarioIcono, "src\\IMG\\usuario_icono.png");
    }
    
    public void iniciar(){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_base = new javax.swing.JPanel();
        lbl_header = new javax.swing.JLabel();
        lbl_tituloIniciarSesion = new javax.swing.JLabel();
        pnl_containerCredentials = new Utils.PanelShadow();
        lbl_usuario = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        lbl_clave = new javax.swing.JLabel();
        btn_verClave = new javax.swing.JToggleButton();
        txt_clave = new javax.swing.JPasswordField();
        pnl_IniciarSesion = new Utils.PanelShadow();
        lbl_usuarioIcono = new javax.swing.JLabel();
        lbl_txtIniciarSesion = new javax.swing.JLabel();
        pnl_footer = new javax.swing.JPanel();
        lbl_footer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fisei Prestamos | Login");
        setResizable(false);
        setSize(new java.awt.Dimension(720, 650));

        pnl_base.setBackground(new java.awt.Color(254, 254, 254));
        pnl_base.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnl_base.add(lbl_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 140));

        lbl_tituloIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_tituloIniciarSesion.setForeground(new java.awt.Color(173, 39, 46));
        lbl_tituloIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloIniciarSesion.setText("INICIAR SESION");
        pnl_base.add(lbl_tituloIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 500, 60));

        pnl_containerCredentials.setBackground(new java.awt.Color(254, 254, 254));
        pnl_containerCredentials.setShadowSize(10);
        pnl_containerCredentials.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_usuario.setForeground(new java.awt.Color(0, 0, 0));
        lbl_usuario.setText("Usuario");
        pnl_containerCredentials.add(lbl_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txt_correo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });
        pnl_containerCredentials.add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 540, 40));

        lbl_clave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_clave.setForeground(new java.awt.Color(0, 0, 0));
        lbl_clave.setText("Contraseña");
        pnl_containerCredentials.add(lbl_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        btn_verClave.setText("ver");
        btn_verClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_containerCredentials.add(btn_verClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 205, 50, 30));

        txt_clave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_claveKeyTyped(evt);
            }
        });
        pnl_containerCredentials.add(txt_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 540, 40));

        pnl_IniciarSesion.setBackground(new java.awt.Color(173, 39, 46));
        pnl_IniciarSesion.setShadowOpacity(0.0F);
        pnl_IniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_IniciarSesionMouseClicked(evt);
            }
        });
        pnl_IniciarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_usuarioIcono.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_IniciarSesion.add(lbl_usuarioIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 20, 20));

        lbl_txtIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        lbl_txtIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_txtIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_txtIniciarSesion.setText("Iniciar Sesión");
        lbl_txtIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_IniciarSesion.add(lbl_txtIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 5, -1, 40));

        pnl_containerCredentials.add(pnl_IniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 150, 50));

        pnl_base.add(pnl_containerCredentials, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 220, 600, 388));

        pnl_footer.setBackground(new java.awt.Color(64, 64, 65));
        pnl_footer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_footer.setForeground(new java.awt.Color(255, 255, 255));
        lbl_footer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_footer.setText("© 2024 | Equi-pro | Prestamo de espacios");
        pnl_footer.add(lbl_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 20));

        pnl_base.add(pnl_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 590, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_base, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnl_IniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_IniciarSesionMouseClicked
        
        this.correo = this.txt_correo.getText().trim();
        this.clave = ManejoComp.claveToString(this.txt_clave);
        
        if(this.correo.equals("") || this.clave.equals("")){
            JOptionPane.showMessageDialog(this, "Correo o clave vacia, intente otra vez. . .");
            return;
        }
        
        bd_login login = new bd_login();
        Encript encript = new Encript();
        this.clave = encript.Encriptar(this.clave);
        if(login.login(this.correo, this.clave) == false){
            JOptionPane.showMessageDialog(this, "Correo o clave incorrecta, intenta de nuevo. . .");
            return;
        }
        this.txt_correo.setText("");
        this.txt_clave.setText("");
        // C/vQhACyrp6Sr4PI8gHZrg==
        // COLOAR LANZAMIENTO DE NUEVA VENTANA AQUI ============================
    }//GEN-LAST:event_pnl_IniciarSesionMouseClicked

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
        if(evt.getKeyChar() == ' '){
            evt.consume();
        }
    }//GEN-LAST:event_txt_correoKeyTyped

    private void txt_claveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_claveKeyTyped
        if(evt.getKeyChar() == ' '){
            evt.consume();
        }
    }//GEN-LAST:event_txt_claveKeyTyped

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_verClave;
    private javax.swing.JLabel lbl_clave;
    private javax.swing.JLabel lbl_footer;
    private javax.swing.JLabel lbl_header;
    private javax.swing.JLabel lbl_tituloIniciarSesion;
    private javax.swing.JLabel lbl_txtIniciarSesion;
    private javax.swing.JLabel lbl_usuario;
    private javax.swing.JLabel lbl_usuarioIcono;
    private Utils.PanelShadow pnl_IniciarSesion;
    private javax.swing.JPanel pnl_base;
    private Utils.PanelShadow pnl_containerCredentials;
    private javax.swing.JPanel pnl_footer;
    private javax.swing.JPasswordField txt_clave;
    private javax.swing.JTextField txt_correo;
    // End of variables declaration//GEN-END:variables
}
