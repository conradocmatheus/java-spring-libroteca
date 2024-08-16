package app.library.controller;

import app.library.entity.Genre;
import app.library.exception.ApiError;
import app.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Genre genre) {
        try {
            String msg = genreService.save(genre);
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving genre",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<Genre> genres) {
        try {
            List<Genre> savedGenres = genreService.saveAll(genres);
            return new ResponseEntity<>(savedGenres, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving genres",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Genre genre) {
        try {
            String msg = genreService.update(genre, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error updating genre",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!genreService.existsById(id)) {
                ApiError apiError = new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        "Genre not found",
                        "Genre with ID " + id + " does not exist"
                );
                return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
            }
            String msg = genreService.delete(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting genre",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        try {
            genreService.deleteAll();
            return new ResponseEntity<>("All genres have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting all genres",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAll() {
        try {
            List<Genre> genres = genreService.listAll();
            return new ResponseEntity<>(genres, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error listing genres",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Genre genre = genreService.findById(id);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Genre not found",
                    "Genre with ID " + id + " does not exist"
            );
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error finding genre",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }
}
