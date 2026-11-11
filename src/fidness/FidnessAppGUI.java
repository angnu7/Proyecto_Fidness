package fidness;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;

public class FidnessAppGUI extends JFrame {
    private Usuario usuarioActual;
    private GestorDeEjercicio gestor;
    private Rutina miRutina;

    private DefaultListModel<Ejercicio> modeloCatalogo;
    private JList<Ejercicio> listaCatalogo;
    private JTextArea txtInstrucciones;
    private DefaultListModel<Ejercicio> modeloMiRutina;
    private JList<Ejercicio> listaMiRutina;

    public FidnessAppGUI() {

        usuarioActual = new Usuario("U1", "Cliente gym", "cliente01@fidness.com", "2468");
        gestor = new GestorDeEjercicio();
        miRutina = new Rutina("R1", "Rutina personalizada");

        mostrarLoginFrame();
    }


    private void mostrarLoginFrame() {
        setTitle("Fidness -- acceso");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblCorreo = new JLabel("Usuario:");
        JTextField txtCorreo = new JTextField();
        JLabel lblContra = new JLabel("Contraseña:");
        JPasswordField txtContra = new JPasswordField();
        JButton btnIniciar = new JButton("Iniciar Sesión");

        btnIniciar.addActionListener((ActionEvent e) -> {
            String correo = txtCorreo.getText();
            String contra = new String(txtContra.getPassword());
            if (usuarioActual.iniciarSesion(correo, contra)) {
                JOptionPane.showMessageDialog(this, "Bienvenido " + usuarioActual.getNombre());
                dispose(); 
                mostrarFramesPrincipales();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(lblCorreo); add(txtCorreo);
        add(lblContra); add(txtContra);
        add(new JLabel("")); add(btnIniciar);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

  
    private void mostrarFramesPrincipales() {
        JFrame mainFrame = new JFrame("Fidness - Panel Principal");
        mainFrame.setSize(800, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(1, 2)); 

     
        JPanel panelCatalogo = new JPanel(new BorderLayout());
        panelCatalogo.setBorder(BorderFactory.createTitledBorder("Catálogo de Ejercicios"));

        JComboBox<String> cmbCategoria = new JComboBox<>(new String[]{"Todos", "Pierna", "Brazo", "Espalda"});
        modeloCatalogo = new DefaultListModel<>();
        actualizarCatalogo("Todos");
        listaCatalogo = new JList<>(modeloCatalogo);
        txtInstrucciones = new JTextArea(5, 20);
        txtInstrucciones.setEditable(false);
        txtInstrucciones.setLineWrap(true);
        JButton btnAgregar = new JButton("Agregar a mi Rutina");

        cmbCategoria.addActionListener(e -> actualizarCatalogo(cmbCategoria.getSelectedItem().toString()));
        listaCatalogo.addListSelectionListener(e -> {
            Ejercicio seleccionado = listaCatalogo.getSelectedValue();
            if (seleccionado != null) txtInstrucciones.setText(seleccionado.getInstrucciones());
        });
        btnAgregar.addActionListener(e -> {
            Ejercicio seleccionado = listaCatalogo.getSelectedValue();
            if (seleccionado != null) {
                miRutina.agregarEjercicio(seleccionado);
                modeloMiRutina.addElement(seleccionado);
            }
        });

        panelCatalogo.add(cmbCategoria, BorderLayout.NORTH);
        panelCatalogo.add(new JScrollPane(listaCatalogo), BorderLayout.CENTER);
        JPanel panelSurCat = new JPanel(new BorderLayout());
        panelSurCat.add(new JScrollPane(txtInstrucciones), BorderLayout.CENTER);
        panelSurCat.add(btnAgregar, BorderLayout.SOUTH);
        panelCatalogo.add(panelSurCat, BorderLayout.SOUTH);

 
        JPanel panelRutina = new JPanel(new BorderLayout());
        panelRutina.setBorder(BorderFactory.createTitledBorder("Mi Rutina"));
        
        JTextField txtNombreRutina = new JTextField(miRutina.getNombreRutina()); 
        txtNombreRutina.setEditable(false);
        modeloMiRutina = new DefaultListModel<>();
        
        listaMiRutina = new JList<>(modeloMiRutina);
        
        JPanel panelBotonesRutina = new JPanel(new GridLayout(1, 2));
        JButton btnEliminar = new JButton("Eliminar Ejercicio");
        JButton btnExportar = new JButton("Exportar Rutina a Archivo");

        btnEliminar.addActionListener(e -> {
            Ejercicio seleccionado = listaMiRutina.getSelectedValue();
            if (seleccionado != null) {
                miRutina.eliminarEjercicio(seleccionado);
                modeloMiRutina.removeElement(seleccionado);
            }
        });

        btnExportar.addActionListener(e -> {
            try {
                miRutina.exportarRutina("Mi_Rutina_Fidness");
                JOptionPane.showMessageDialog(mainFrame, "Rutina exportada exitosamente como Mi_Rutina_Fidness.txt");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error al exportar", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelBotonesRutina.add(btnEliminar);
        panelBotonesRutina.add(btnExportar);

        panelRutina.add(txtNombreRutina, BorderLayout.NORTH);
        panelRutina.add(new JScrollPane(listaMiRutina), BorderLayout.CENTER);
        panelRutina.add(panelBotonesRutina, BorderLayout.SOUTH);


        mainFrame.add(panelCatalogo);
        mainFrame.add(panelRutina);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void actualizarCatalogo(String categoria) {
        modeloCatalogo.clear();
        List<Ejercicio> lista = categoria.equals("Todos") ? gestor.obtenerTodos() : gestor.buscarPorCategoria(categoria);
        for (Ejercicio ej : lista) {
            modeloCatalogo.addElement(ej);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FidnessAppGUI());
    }
}
