package fidness;

public abstract class Persona {
    protected String nombre;
    protected String correo;
    
    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }
    
    public abstract String getIdentificacion();
    
    public String getNombre() { return nombre; }
}
