/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;


public class Validaciones {

    // Validar que la cédula sea solo números y que pertenezca de Ecuador Papá
    public static boolean validarCedula(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false;
        }
        // Algoritmo para validar cédula de EC
        int total = 0;
        int longitud = cedula.length();
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};

        for (int i = 0; i < longitud - 1; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i)) * coeficientes[i];
            total += (digito > 9) ? digito - 9 : digito;
        }

        int ultimoDigito = Character.getNumericValue(cedula.charAt(longitud - 1));
        return (total % 10 == 0 ? 0 : 10 - total % 10) == ultimoDigito;
    }

    // validacion del name
    public static boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    // Validar que el correo contenga un '@'
    public static boolean validarCorreo(String correo) {
        return correo != null && correo.contains("@");
    }
}
