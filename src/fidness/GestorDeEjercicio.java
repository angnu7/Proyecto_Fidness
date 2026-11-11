package fidness;

import java.util.ArrayList;
import java.util.List;

public class GestorDeEjercicio {
    private List<Ejercicio> catalogoEjercicios;

    public GestorDeEjercicio() {
        catalogoEjercicios = new ArrayList<>();
        cargarDatosPrueba();
    }

    public void agregarAlCatalogo(Ejercicio ejercicio) {
        catalogoEjercicios.add(ejercicio);
    }

    public List<Ejercicio> buscarPorCategoria(String categoria) {
        List<Ejercicio> filtrados = new ArrayList<>();
        for (Ejercicio ej : catalogoEjercicios) {
            if (ej.getCategoria().equalsIgnoreCase(categoria)) {
                filtrados.add(ej);
            }
        }
        return filtrados;
    }

    public List<Ejercicio> obtenerTodos() {
        return catalogoEjercicios;
    }

    private void cargarDatosPrueba() {
        agregarAlCatalogo(new Ejercicio("1", "Sentadilla", "Pierna", "Ejercicio de fuerza", "Mantener la espalda recta, bajar controladamente hasta 90 grados y subir..."));
        agregarAlCatalogo(new Ejercicio("2", "Prensa", "Pierna", "Máquina de piernas", "Empujar el peso con las piernas sin bloquear las rodillas."));
        agregarAlCatalogo(new Ejercicio("3", "Curl de Bíceps", "Brazo", "Ejercicio de aislamiento", "Flexionar los codos levantando el peso hacia los hombros."));
    }
}
