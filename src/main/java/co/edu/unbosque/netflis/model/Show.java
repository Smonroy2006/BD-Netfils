package co.edu.unbosque.netflis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    private String showId;
    private String type;
    private String title;
    private String director;
    private String cast;
    private String country;     // <--- Este faltaba
    private String date;        // date_added
    private String releaseYear;
    private String rating;
    private String duration;
    private String listedIn;
    private String description;



}
