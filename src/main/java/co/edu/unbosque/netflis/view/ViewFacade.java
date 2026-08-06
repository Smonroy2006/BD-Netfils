package co.edu.unbosque.netflis.view;

import javax.swing.JFrame;

public class ViewFacade extends JFrame {

    private ShowTableView panelTabla;

    public ViewFacade() {
        setTitle("Netflis - Catálogo de Contenidos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(true);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelTabla = new ShowTableView();
        // Agregamos el panel principal a la ventana
        add(panelTabla);
    }

    // Getter para que el Controller pueda acceder a los métodos del panel (actualizar tabla, leer textos, etc.)
    public ShowTableView getPanelTabla() {
        return panelTabla;
    }
}