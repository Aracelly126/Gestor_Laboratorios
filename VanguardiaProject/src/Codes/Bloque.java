/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codes;

/**
 *
 * @author Arita
 */
public class Bloque {
    private  int idBloque;
    private String nombre;
    public Bloque(int idBloque,String nombre){
        this.idBloque=idBloque;
        this.nombre=nombre;
    }

    public void setIdBloque(int idBloque) {
        this.idBloque = idBloque;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId(){
        return idBloque;
    }
    public String getNombre(){
        return nombre;
    }
    
}
