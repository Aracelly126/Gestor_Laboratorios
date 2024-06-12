 package Utils;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conex {
    

    private static int puerto = 3306;
    private static String url = "jdbc:mysql://localhost:" + puerto + "/";
    private static String nombre_db = "vanguardia";
    private static String user = "root";
    private static String pass = "StevenLoor24";

    private static Connection con;

    private Conex() {
        this.conectar();
    }

    static {
        try {
            Conex c = new Conex();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection fail 1. . .");
            System.exit(0);
        }
    }

    private void conectar() {
        try {
            con = DriverManager.getConnection(url + nombre_db, user, pass);
            System.out.println("Connection success");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection fail 2. . .");
            System.exit(0);
        }
    }

    public static Connection getConex() {
        try {
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection fail 3. . .");
            System.exit(0);
        }
        return null;
    }
}
