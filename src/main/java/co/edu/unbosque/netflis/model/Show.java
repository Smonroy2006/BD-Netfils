package co.edu.unbosque.netflis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @CsvBindByName(column = "show_id")
    private String showId;

    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "director")
    private String director;

    @CsvBindByName(column = "cast")
    private String cast;

    @CsvBindByName(column = "country")
    private String country;

    @CsvBindByName(column = "date_added")
    private String date;

    @CsvBindByName(column = "release_year")
    private String releaseYear;

    @CsvBindByName(column = "rating")
    private String rating;

    @CsvBindByName(column = "duration")
    private String duration;

    @CsvBindByName(column = "listed_in")
    private String listedIn;

    @CsvBindByName(column = "description")
    private String description;



}
