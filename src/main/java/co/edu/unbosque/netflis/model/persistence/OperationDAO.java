package co.edu.unbosque.netflis.model.persistence;

import java.util.List;

public interface OperationDAO <D,E>{

    public boolean add(D object);
    public D findById(String id);
    public List<D> findByType(String type);
    public List<D> findByTitle(String title);
    public List<D> findByDirector(String director);
    public List<D> findByCast(String cast);
    public List<D> findByCountry(String country);
    public List<D> findByDateAdded(String dateAdded);
    public List<D> findByReleaseYear(int releaseYear);
    public List<D> findByRating(String rating);
    public List<D> findByDuration(String duration);
    public List<D> findByListedIn(String listedIn);
    public List<D> findByDescription(String description);


}
