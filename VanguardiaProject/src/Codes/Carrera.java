/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codes;

/**
 *
 * @author USER
 */
public class Carrera {
    private int idCarrera;
    private String nombreCarrera;

    public Carrera(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    @Override
    public String toString() {
        return nombreCarrera; // Importante para mostrar en el JComboBox
    }
}