package Codes;

public class Materia {
    private int idMateria;
    private String nombre;
    private int semestreId;

    public Materia(int idMateria, String nombre, int semestreId) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.semestreId = semestreId;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemestreId(int semestreId) {
        this.semestreId = semestreId;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSemestreId() {
        return semestreId;
    }
}
