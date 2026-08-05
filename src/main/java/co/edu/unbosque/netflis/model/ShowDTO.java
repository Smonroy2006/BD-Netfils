package co.edu.unbosque.netflis.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {

    private String showId;
    private String type;
    private String title;
    private String director;
    private String cast;
    private LocalDate date;
    private String releaseYear;
    private String rating;
    private Integer duration;
    private String listedIn;
    private String description;

}
