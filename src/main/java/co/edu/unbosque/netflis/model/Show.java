package co.edu.unbosque.netflis.model;

import com.opencsv.bean.CsvBindByName;
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


    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListedIn() {
        return listedIn;
    }

    public void setListedIn(String listedIn) {
        this.listedIn = listedIn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
