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
        
        add(panelTabla);
    }

    public ShowTableView getPanelTabla() {
        return panelTabla;
    }
}
