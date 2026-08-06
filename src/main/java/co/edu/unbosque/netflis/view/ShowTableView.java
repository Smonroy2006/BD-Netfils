package co.edu.unbosque.netflis.view;

import co.edu.unbosque.netflis.model.ShowDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowTableView extends JPanel {

    private JComboBox<String> cbCriterio;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JTable tabla;
    private DefaultTableModel model;

    public ShowTableView() {
        setLayout(new BorderLayout(10, 10));
        initComponentes();
    }

    private void initComponentes() {
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        panelFiltros.add(new JLabel("Buscar por:"));

        String[] opciones = {
                "Todos", "ID", "Título", "Director", "Reparto",
                "Tipo", "País", "Categoría", "Rating", "Año"
        };
        cbCriterio = new JComboBox<>(opciones);
        panelFiltros.add(cbCriterio);

        txtBusqueda = new JTextField(20);
        panelFiltros.add(txtBusqueda);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setActionCommand("BUSCAR");

        btnLimpiar = new JButton("Limpiar / Ver Todos");
        btnLimpiar.setActionCommand("LIMPIAR");

        panelFiltros.add(btnBuscar);
        panelFiltros.add(btnLimpiar);

        add(panelFiltros, BorderLayout.NORTH);

        String[] columnas = {
                "ID", "Type", "Title", "Director", "Cast",
                "Country", "Date", "Year", "Rating", "Duration",
                "Listed In", "Description"
        };

        model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(model);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Permite scroll horizontal para las 12 columnas

        // Ajuste básico de ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);   // Type
        tabla.getColumnModel().getColumn(2).setPreferredWidth(180);  // Title
        tabla.getColumnModel().getColumn(3).setPreferredWidth(140);  // Director
        tabla.getColumnModel().getColumn(4).setPreferredWidth(180);  // Cast
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);  // Country
        tabla.getColumnModel().getColumn(6).setPreferredWidth(120);  // Date
        tabla.getColumnModel().getColumn(7).setPreferredWidth(60);   // Year
        tabla.getColumnModel().getColumn(8).setPreferredWidth(60);   // Rating
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);   // Duration
        tabla.getColumnModel().getColumn(10).setPreferredWidth(150); // Listed In
        tabla.getColumnModel().getColumn(11).setPreferredWidth(300); // Description

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public void actualizarTabla(List<ShowDTO> listaShows) {
        model.setRowCount(0); // Limpia datos previos
        if (listaShows == null || listaShows.isEmpty()) return;

        for (ShowDTO s : listaShows) {
            Object[] fila = {
                    s.getShowId(),
                    s.getType(),
                    s.getTitle(),
                    s.getDirector(),
                    s.getCast(),
                    s.getCountry(),
                    s.getDate(),
                    s.getReleaseYear(),
                    s.getRating(),
                    s.getDuration(),
                    s.getListedIn(),
                    s.getDescription()
            };
            model.addRow(fila);
        }
    }
    public String getCriterioSeleccionado() {
        return (String) cbCriterio.getSelectedItem();
    }

    public String getTextoBusqueda() {
        return txtBusqueda.getText().trim();
    }

    public void limpiarBusqueda() {
        txtBusqueda.setText("");
        cbCriterio.setSelectedIndex(0);
    }

    public void addBotonBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
        txtBusqueda.addActionListener(listener);
    }

    public void addBotonLimpiarListener(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }
}