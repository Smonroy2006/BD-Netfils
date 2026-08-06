package co.edu.unbosque.netflis.view;

import javax.swing.*;

public class ViewFacade extends JFrame {

    private ShowTableView panelTabla;
    private ShowFormView panelForm;
    private JTabbedPane pestañas;

    public ViewFacade() {
        setTitle("Netflis - Catálogo de Contenidos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(true);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        pestañas = new JTabbedPane();
        
        panelTabla = new ShowTableView();
        panelForm = new ShowFormView();
        
        pestañas.addTab("Catálogo", panelTabla);
        pestañas.addTab("Nuevo Registro", panelForm);
        
        add(pestañas);
    }

    public ShowTableView getPanelTabla() {
        return panelTabla;
    }

    public ShowFormView getPanelForm() {
        return panelForm;
    }
}
