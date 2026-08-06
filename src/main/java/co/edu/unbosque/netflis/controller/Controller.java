package co.edu.unbosque.netflis.controller;

import co.edu.unbosque.netflis.model.ShowDTO;
import co.edu.unbosque.netflis.model.persistence.ShowDAO;
import co.edu.unbosque.netflis.view.ViewFacade;
import org.modelmapper.ModelMapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Controller implements ActionListener {

    private ViewFacade view;
    private ShowDAO dao;
    private ModelMapper modelMapper;

    public Controller() {
        view = new ViewFacade();
        dao = new ShowDAO();
        modelMapper = new ModelMapper();

        asignarOyentes();
        iniciarAplicacion();
    }

    private void asignarOyentes() {
        view.getPanelTabla().addBotonBuscarListener(this);
        view.getPanelTabla().addBotonLimpiarListener(this);
        view.getPanelForm().addActionListener(this);
    }

    private void iniciarAplicacion() {
        dao.load();
        refrescarTabla();
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "BUSCAR":
                ejecutarBusqueda();
                break;
            case "LIMPIAR":
                view.getPanelTabla().limpiarBusqueda();
                refrescarTabla();
                break;
            case "GUARDAR":
                ejecutarGuardado();
                break;
            case "LIMPIAR_FORM":
                view.getPanelForm().limpiarCampos();
                break;
        }
    }

    private void ejecutarGuardado() {
        // Validación básica en el controlador
        if (view.getPanelForm().getShowId().isEmpty() || view.getPanelForm().getTitle().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(view, "El ID y el Título son campos obligatorios.");
            return;
        }

        // Recolectar datos de la vista
        ShowDTO nuevo = new ShowDTO();
        nuevo.setShowId(view.getPanelForm().getShowId());
        nuevo.setType(view.getPanelForm().getType());
        nuevo.setTitle(view.getPanelForm().getTitle());
        nuevo.setDirector(view.getPanelForm().getDirector());
        nuevo.setCast(view.getPanelForm().getCast());
        nuevo.setCountry(view.getPanelForm().getCountry());
        nuevo.setDate(view.getPanelForm().getDate());
        nuevo.setReleaseYear(view.getPanelForm().getReleaseYear());
        nuevo.setRating(view.getPanelForm().getRating());
        nuevo.setDuration(view.getPanelForm().getDuration());
        nuevo.setListedIn(view.getPanelForm().getListedIn());
        nuevo.setDescription(view.getPanelForm().getDescription());

        if (dao.add(nuevo)) {
            dao.persist();
            javax.swing.JOptionPane.showMessageDialog(view, "Registro guardado y persistido correctamente.");
            view.getPanelForm().limpiarCampos();
            refrescarTabla();
        }
    }

    private void ejecutarBusqueda() {
        String criterio = view.getPanelTabla().getCriterioSeleccionado();
        String texto = view.getPanelTabla().getTextoBusqueda();
        List<ShowDTO> resultados;

        if (texto.isEmpty() || criterio.equals("Todos")) {
            refrescarTabla();
            return;
        }

        switch (criterio) {
            case "ID" -> {
                ShowDTO dto = dao.findById(texto);
                resultados = (dto != null) ? List.of(dto) : List.of();
            }
            case "Título" -> resultados = dao.findByTitle(texto);
            case "Director" -> resultados = dao.findByDirector(texto);
            case "Reparto" -> resultados = dao.findByCast(texto);
            case "Tipo" -> resultados = dao.findByType(texto);
            case "País" -> resultados = dao.findByCountry(texto);
            case "Categoría" -> resultados = dao.findByListedIn(texto);
            case "Rating" -> resultados = dao.findByRating(texto);
            case "Año" -> {
                try {
                    int anio = Integer.parseInt(texto);
                    resultados = dao.findByReleaseYear(anio);
                } catch (NumberFormatException ex) {
                    resultados = List.of();
                }
            }
            default -> resultados = List.of();
        }

        view.getPanelTabla().actualizarTabla(resultados);
    }

    public void refrescarTabla() {
        List<ShowDTO> todos = dao.getShows().stream()
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());

        view.getPanelTabla().actualizarTabla(todos);
    }
}
