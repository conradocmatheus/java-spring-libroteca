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

    // Save more Genres
    public List<Genre> saveAll(List<Genre> genres) {
        return genreRepository.saveAll(genres);
    }

    // Update a Genre by ID
    public String update(Genre genre, Long id) {
        genre.setId(id);
        genreRepository.save(genre);
        return genre.getName() + " successfully updated!";
    }

    // Delete a Genre by ID
    public String delete(Long id) {
        genreRepository.deleteById(id);
        return "Genre with id: " + id + " deleted";
    }

    // Delete all Genres
    public void deleteAll() {
        genreRepository.deleteAll();
    }

    // List all Genres
    public List<Genre> listAll() {
        return genreRepository.findAll();
    }

    // Find a Genre by ID
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }

    // Verify genre existence by ID
    public boolean existsById(Long id) {
        return genreRepository.existsById(id);
    }
}
