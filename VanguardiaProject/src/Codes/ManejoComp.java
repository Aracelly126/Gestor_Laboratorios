package Codes;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ManejoComp {

    private ManejoComp() {
        // Private constructor to prevent instantiation
    }

    public static void crearlabel(JLabel label, String nombreImg) {
        label.setIcon(redimensionarImagen(nombreImg, label.getWidth(), label.getHeight()));
    }

    public static void crearPanel(JPanel panel, String nombreImg) {
        ImageIcon imagen = redimensionarImagen(nombreImg, panel.getWidth(), panel.getHeight());

        JLabel label = new JLabel(imagen);
        label.setOpaque(false);

        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        panel.add(label, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }

    // no implementado xq se buguea con sus componentes hijos. . .
    public static void crearPanelTrans(JPanel panel) {
        Color colorTrans = new Color(255, 0, 0, (int) 0.5);
        panel.setBackground(colorTrans);
    }

    public static void crearFrameTrans(JFrame frame) {
        Color colorTrans = new Color(255, 0, 0, (int) 0.5);
        frame.setBackground(colorTrans);
    }

    public static void crearBoton(JLabel label, String nombreImg, String nombreImgHover) {
        label.setIcon(redimensionarImagen(nombreImg, label.getWidth(), label.getHeight()));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setIcon(redimensionarImagen(nombreImgHover, label.getWidth(), label.getHeight()));
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setIcon(redimensionarImagen(nombreImg, label.getWidth(), label.getHeight()));
                label.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    public static void colorBackgroundCmbx(JComboBox cmb, Color colorBad, Color colorGod) {
        cmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb.setBackground(colorGod);
            }
        });
        cmb.setBackground(colorBad);
    }

    public static String claveToString(JPasswordField p) {
        char[] passwordChars = p.getPassword();
        return new String(passwordChars);
    }

    public static void colorBorderTxt(JTextField txt, Color colorBad, Color colorGod) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt.setBorder(new LineBorder(colorGod, 3));
            }
        });
        txt.setBorder(new LineBorder(colorBad, 3));
    }

    public static void colorBackgroundTxt(JTextField txt, Color colorBad, Color colorGod) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt.setBackground(colorGod);
            }
        });
        txt.setBackground(colorBad);
    }

    public static void crearVerPassword(JPasswordField pas, JToggleButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton btn = (JToggleButton) e.getSource();
                if (btn.isSelected()) {
                    pas.setEchoChar((char) 0);
                } else {
                    pas.setEchoChar('\u2022');
                }
            }
        });
    }

    public static void vaciarTabla(JTable tbl, DefaultTableModel modelito) {
        int count = tbl.getRowCount();
        for (int i = count; i > 0; i--) {
            modelito.removeRow(i - 1);
        }
    }

    public static void txtNotSpecialCharacters(java.awt.event.KeyEvent evt) {
        if (!Character.isLetterOrDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }

    public static void txtOnlyLetters(java.awt.event.KeyEvent evt) {
        if (!Character.isAlphabetic(evt.getKeyChar())) {
            evt.consume();
        }
    }

    public static void txtOnlyLettersSpaces(java.awt.event.KeyEvent evt) {
        if (!(Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyChar() == ' ')) {
            evt.consume();
        }
    }

    public static boolean txtOnlyNumbers(java.awt.event.KeyEvent evt) {
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            return false;
        }
        return true;
    }

    public static void txtOnlyDoubles(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == '.')) {
            evt.consume();
        }
    }

    public static void txtTypingNumsDec(JTextField txt, java.awt.event.KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (!(caracter >= '0' && caracter <= '9') && caracter != '.') {
            evt.consume();
        }
        if (txt.getText().contains(".") && caracter == '.') {
            evt.consume();
        }
        if (txt.getText().contains(".")) {
            if (txt.getText().length() > txt.getText().indexOf(".") + 2) {
                evt.consume();
            }
            if (!(txt.getText().matches("^\\d+(\\.\\d{0,1})?$"))) {
                evt.consume();
            }
        }
    }

    public static ImageIcon redimensionarImagen(String rutaImagen, int ancho, int alto) {
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);
        Image imagen = imagenIcon.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        if (imagenRedimensionada == null) {
            System.out.println("Sin imagen");
        }
        return new ImageIcon(imagenRedimensionada);
    }

    public static String[] arrayObjectsToArrayString(Object[] arrayObjetos) {
        String[] arrayString = new String[arrayObjetos.length];

        for (int i = 0; i < arrayObjetos.length; i++) {
            Object objeto = arrayObjetos[i];
            String cadena = objeto.toString();

            arrayString[i] = cadena;
        }
        return arrayString;
    }

    public static void txtOnlyLettersSpacesCondicion(KeyEvent evt, int num) {
        char keyChar = evt.getKeyChar();

        if (!Character.isAlphabetic(keyChar) && keyChar != KeyEvent.VK_SPACE) {
            evt.consume();
        } else if (keyChar == KeyEvent.VK_SPACE) {
            if (textContainsMultipleSpaces(evt, num)) {
                evt.consume();
            }
        }
    }

    public static void txtNoEspecialsCharacterSpace(KeyEvent evt, int num) {
        char keyChar = evt.getKeyChar();

        if (!Character.isLetter(keyChar) && !Character.isWhitespace(keyChar) && !Character.isDigit(keyChar)) {
            evt.consume();
        } else if (Character.isWhitespace(keyChar)) {
            if (textContainsMultipleSpaces(evt, num)) {
                evt.consume();
            }
        }
    }

    private static boolean textContainsMultipleSpaces(KeyEvent evt, int num) {
        String text = ((JTextField) evt.getSource()).getText();
        int spaceCount = 0;
        boolean previousSpace = false;

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                if (previousSpace) {
                    return true;
                }
                spaceCount++;
                previousSpace = true;
            } else {
                previousSpace = false;
            }
        }

        return spaceCount >= num;
    }

    public static int StringToInt(JTextField txt) {
        try {
            int resultado = Integer.parseInt(txt.getText().trim());
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }

    public static double StringToDouble(JTextField txt) {
        try {
            double resultado = Double.parseDouble(txt.getText().trim());

            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String resultadoFormateado = decimalFormat.format(resultado);

            return Double.parseDouble(resultadoFormateado);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void txtLongitudCondicion(JTextField jtextfield, KeyEvent evt, int longitud) {
        String cadena = jtextfield.getText();
        if (cadena.length() >= longitud) {
            evt.consume();
        }
    }

}
