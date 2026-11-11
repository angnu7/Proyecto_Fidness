package fidness;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String idRutina;
    private String nombreRutina;
    private List<Ejercicio> listaEjercicios;

    public Rutina(String idRutina, String nombreRutina) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.listaEjercicios = new ArrayList<>(); 
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        listaEjercicios.add(ejercicio);
    }

    public void eliminarEjercicio(Ejercicio ejercicio) {
        listaEjercicios.remove(ejercicio);
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }
    
    public void exportarRutina(String nombreArchivo) throws Exception {
        if (listaEjercicios.isEmpty()) {
            throw new Exception("La rutina no tiene ejercicios para exportar.");
        }
        
        try (FileWriter writer = new FileWriter(nombreArchivo + ".txt")) {
            writer.write("Rutina: " + nombreRutina + "\n\n");
            for (Ejercicio ej : listaEjercicios) {
                writer.write("- " + ej.getNombre() + " (" + ej.getCategoria() + ")\n");
                writer.write("  Instrucciones: " + ej.getInstrucciones() + "\n\n");
            }
        } catch (IOException e) {
            throw new Exception("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
