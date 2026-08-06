package co.edu.unbosque.netflis.model.persistence;


import co.edu.unbosque.netflis.model.Show;
import co.edu.unbosque.netflis.model.ShowDTO;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.Getter;
import org.modelmapper.ModelMapper;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowDAO implements OperationDAO<ShowDTO, Show>{

    private static final String FILE_NAME = "catalogo.txt";
    private static final char DELIMITER = '|';
    @Getter
    private List<Show> shows = new ArrayList<>();
    private ModelMapper modelMapper;

    public ShowDAO() {
        this.modelMapper = new ModelMapper();
        load();
    }

    @Override
    public boolean add(ShowDTO object) {
        if (object == null) return false;
        Show show = modelMapper.map(object, Show.class);
        return shows.add(show);
    }

    @Override
    public ShowDTO findById(String id) {
        if (id == null || id.isBlank()) return null;
        return shows.stream()
                .filter(s -> s.getShowId() != null && s.getShowId().equalsIgnoreCase(id.trim()))
                .findFirst()
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .orElse(null);
    }

    @Override
    public List<ShowDTO> findByType(String type) {
        if (type == null || type.isBlank()) return List.of();
        return shows.stream()
                .filter(s -> s.getType() != null && s.getType().equalsIgnoreCase(type.trim()))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByTitle(String title) {
        if (title == null || title.isBlank()) return List.of();
        String query = title.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getTitle() != null && s.getTitle().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByDirector(String director) {
        if (director == null || director.isBlank()) return List.of();
        String query = director.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getDirector() != null && s.getDirector().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByCast(String cast) {
        if (cast == null || cast.isBlank()) return List.of();
        String query = cast.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getCast() != null && s.getCast().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByCountry(String country) {
        if (country == null || country.isBlank()) return List.of();
        String query = country.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getCountry() != null && s.getCountry().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByDateAdded(String dateAdded) {
        if (dateAdded == null || dateAdded.isBlank()) return List.of();
        String query = dateAdded.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getDate() != null && s.getDate().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByReleaseYear(int releaseYear) {
        String yearStr = String.valueOf(releaseYear);
        return shows.stream()
                .filter(s -> s.getReleaseYear() != null && s.getReleaseYear().trim().equals(yearStr))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByRating(String rating) {
        if (rating == null || rating.isBlank()) return List.of();
        return shows.stream()
                .filter(s -> s.getRating() != null && s.getRating().equalsIgnoreCase(rating.trim()))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByDuration(String duration) {
        if (duration == null || duration.isBlank()) return List.of();
        String query = duration.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getDuration() != null && s.getDuration().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByListedIn(String listedIn) {
        if (listedIn == null || listedIn.isBlank()) return List.of();
        String query = listedIn.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getListedIn() != null && s.getListedIn().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> findByDescription(String description) {
        if (description == null || description.isBlank()) return List.of();
        String query = description.toLowerCase().trim();
        return shows.stream()
                .filter(s -> s.getDescription() != null && s.getDescription().toLowerCase().contains(query))
                .map(s -> modelMapper.map(s, ShowDTO.class))
                .collect(Collectors.toList());
    }

    public void load() {
        shows.clear();
        try {
            Path path = Paths.get("src", "archivos", FILE_NAME);
            Files.createDirectories(path.getParent());

            if (!Files.exists(path)) {
                Files.createFile(path);
                return;
            }

            try (Reader reader = Files.newBufferedReader(path)) {
                List<Show> result = new CsvToBeanBuilder<Show>(reader)
                        .withSeparator(DELIMITER)
                        .withType(Show.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreEmptyLine(true)
                        .build()
                        .parse();

                shows.addAll(result);
                
                // DEBUG
                if (!shows.isEmpty()) {
                    System.out.println("✓ Cargadas " + shows.size() + " filas");
                    System.out.println("Primera fila ID: " + shows.get(0).getShowId());
                    System.out.println("Primera fila Type: " + shows.get(0).getType());
                    System.out.println("Primera fila Title: " + shows.get(0).getTitle());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error cargando " + FILE_NAME + " con OpenCSV", e);
        }
    }

    public void persist() {
        try {
            Path path = Paths.get("src", "archivos", FILE_NAME);
            Files.createDirectories(path.getParent());

            try (Writer writer = Files.newBufferedWriter(path)) {
                StatefulBeanToCsv<Show> beanToCsv = new StatefulBeanToCsvBuilder<Show>(writer)
                        .withSeparator(DELIMITER)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();

                beanToCsv.write(shows);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error persistiendo " + FILE_NAME + " con OpenCSV", e);
        }
    }
}
