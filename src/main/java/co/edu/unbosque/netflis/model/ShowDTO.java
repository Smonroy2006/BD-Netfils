package co.edu.unbosque.netflis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowDTO {

    private String showId;
    private String type;
    private String title;
    private String director;
    private String cast;
    private String country;
    private String date;
    private String releaseYear;
    private String rating;
    private String duration;
    private String listedIn;
    private String description;
}
