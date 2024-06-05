package Main;

import Utils.Conex;
import Windows.Login;


public class Main {

    public static void main(String[] args) {
        Conex.getConex();
        Login login = new Login();
        login.iniciar();
    }
    
}
