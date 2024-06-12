package CodesBD;

public class Reserva {
    private int id;
    private String cedula;
    private String nombreUsuario;
    private String correo;
    private String descripcion;

    public Reserva(int id, String cedula, String nombreUsuario, String correo, String descripcion) {
        this.id = id;
        this.cedula = cedula;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
