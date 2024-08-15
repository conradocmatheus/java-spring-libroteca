package app.library.service;

import app.library.entity.Genre;
import app.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    // Save a Genre
    public String save(Genre genre) {
        genreRepository.save(genre);
        return "Genre: " + genre.getName() + ", successfully saved";
    }

    // Update an Employee by ID
    public String update(Genre genre, Long id) {
        genre.setId(id);
        genreRepository.save(genre);
        return genre.getName() + " successfully updated!";
    }

    // Delete an Employee by ID
    public String delete(Long id) {
        genreRepository.deleteById(id);
        return "Genre with id: " + id + " deleted";
    }

    // List all Employees
    public List<Genre> listAll() {
        return genreRepository.findAll();
    }

    // Find an Employee by ID
    public Genre findById(Long id) {
        return genreRepository.findById(id).get();
    }
}
