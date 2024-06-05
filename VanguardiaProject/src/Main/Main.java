package Main;

import Utils.Conex;
import Windows.Login;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Worldsito");
        Conex.getConex();
        
        Login login = new Login();
        login.iniciar();
    }
    
}
