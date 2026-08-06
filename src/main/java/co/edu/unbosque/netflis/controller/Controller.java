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
    }

    private void iniciarAplicacion() {
        dao.load();
        mostrarTodosLosShows();
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
                mostrarTodosLosShows();
                break;
        }
    }

    private void ejecutarBusqueda() {
        String criterio = view.getPanelTabla().getCriterioSeleccionado();
        String texto = view.getPanelTabla().getTextoBusqueda();
        List<ShowDTO> resultados;

        if (texto.isEmpty() || criterio.equals("Todos")) {
            mostrarTodosLosShows();
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

    private void mostrarTodosLosShows() {
        List<ShowDTO> todos = dao.getShows().stream()
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());

        view.getPanelTabla().actualizarTabla(todos);
    }
}
