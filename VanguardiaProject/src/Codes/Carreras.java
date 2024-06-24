/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codes;

/**
 *
 * @author Arita
 */
public class Carreras {

    private int idCarrera;
    private String nombre;

    public Carreras(int idCarrera, String nombre) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

}
