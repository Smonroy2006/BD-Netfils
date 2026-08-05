package co.edu.unbosque.netflis.model.persistence;


import co.edu.unbosque.netflis.model.Show;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ShowDAO {

    private static final String FILE_NAME = "netflix_titles.txt";
    private static final String SEP = "\\|";
    private List<Show> shows = new ArrayList<>();

    public void load() {
        shows.clear();
        try {
            Path path = Paths.get("src", "archivos", FILE_NAME);
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
                return;
            }

            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) return;

            int start = 0;
            // Detectar header
            if (lines.get(0).toLowerCase().contains("show_id") ||
                    lines.get(0).toLowerCase().contains("listed_in")) {
                start = 1;
            }

            for (int i = start; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line == null || line.trim().isEmpty()) continue;

                // Quitar la coma residual que viene al final de cada línea en el TXT
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1);
                }

                String[] c = line.split(SEP, -1);

                // Validación mínima (deben venir las 12 columnas)
                if (c.length < 12) continue;

                // Mapeo exacto a los 12 atributos del modelo Show (todos String)
                Show s = new Show(
                        c[0].trim(),  // showId
                        c[1].trim(),  // type
                        c[2].trim(),  // title
                        c[3].trim(),  // director
                        c[4].trim(),  // cast
                        c[5].trim(),  // country
                        c[6].trim(),  // date (date_added)
                        c[7].trim(),  // releaseYear
                        c[8].trim(),  // rating
                        c[9].trim(),  // duration
                        c[10].trim(), // listedIn
                        c[11].trim()  // description
                );

                shows.add(s);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error cargando " + FILE_NAME, e);
        }
    }

    public void persist() {
        try {
            Path path = Paths.get("src", "archivos", FILE_NAME);
            Files.createDirectories(path.getParent());

            StringBuilder sb = new StringBuilder();
            sb.append("show_id|type|title|director|cast|country|date_added|release_year|rating|duration|listed_in|description,\n");

            for (Show s : shows) {
                sb.append(s.getShowId()).append("|")
                        .append(s.getType()).append("|")
                        .append(s.getTitle()).append("|")
                        .append(s.getDirector() == null ? "" : s.getDirector()).append("|")
                        .append(s.getCast() == null ? "" : s.getCast()).append("|")
                        .append(s.getCountry() == null ? "" : s.getCountry()).append("|")
                        .append(s.getDate() == null ? "" : s.getDate()).append("|")
                        .append(s.getReleaseYear() == null ? "" : s.getReleaseYear()).append("|")
                        .append(s.getRating() == null ? "" : s.getRating()).append("|")
                        .append(s.getDuration() == null ? "" : s.getDuration()).append("|")
                        .append(s.getListedIn() == null ? "" : s.getListedIn()).append("|")
                        .append(s.getDescription() == null ? "" : s.getDescription())
                        .append(",\n");
            }

            Files.writeString(path, sb.toString(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (Exception e) {
            throw new RuntimeException("Error persistiendo " + FILE_NAME, e);
        }
    }

}
