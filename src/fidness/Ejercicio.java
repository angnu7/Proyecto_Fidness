package fidness;

public class Ejercicio {
    private String idEjercicio;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String instrucciones;

    public Ejercicio(String idEjercicio, String nombre, String categoria, String descripcion, String instrucciones) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.instrucciones = instrucciones;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getInstrucciones() { return instrucciones; }
    
    @Override
    public String toString() { return nombre; } // Facilita mostrarlo en las listas de la GUI
}
