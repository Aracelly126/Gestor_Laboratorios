package CodesBD;

import java.util.Date;

public class Reserva {
    private int id; // ID de la reserva
    private Date fecha;
    private String hora;
    private String cedula;
    private String nombreUsuario;
    private String correo;
    private String observacion;
    private String idEspacio;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    
    public String getIdEspacio() { return idEspacio; }
    public void setIdEspacio(String idEspacio) { this.idEspacio = idEspacio; }
}
