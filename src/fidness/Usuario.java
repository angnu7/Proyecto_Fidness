package fidness;

public class Usuario extends Persona {
    private String idUsuario;
    private String contrasena;

    public Usuario(String idUsuario, String nombre, String correo, String contrasena) {
        super(nombre, correo);
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
    }

    @Override
    public String getIdentificacion() {
        return "Usuario Registrado: " + idUsuario;
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        return this.correo.equals(correo) && this.contrasena.equals(contrasena);
    }

    public String getIdUsuario() { return idUsuario; }
}
