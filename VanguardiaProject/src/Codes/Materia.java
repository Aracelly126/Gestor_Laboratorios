package Codes;

public class Materia {
    private int idMateria;
    private String nombre;
    private int semestreId;
    private int numeroSemestre;  // Nuevo campo
    private int carreraId;       // Nuevo campo
    private String nombreCarrera;// Nuevo campo

    // Constructor actualizado
    public Materia(int idMateria, String nombre, int semestreId, int numeroSemestre, int carreraId, String nombreCarrera) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.semestreId = semestreId;
        this.numeroSemestre = numeroSemestre;
        this.carreraId = carreraId;
        this.nombreCarrera = nombreCarrera;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(int semestreId) {
        this.semestreId = semestreId;
    }

    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
    
}