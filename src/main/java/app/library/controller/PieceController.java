package app.library.controller;

import app.library.entity.Piece;
import app.library.exception.ApiError;
import app.library.service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/piece")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Piece piece) {
        try {
            String msg = this.pieceService.save(piece);
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving piece",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<Piece> pieces) {
        try {
            List<Piece> savedPieces = this.pieceService.saveAll(pieces);
            return new ResponseEntity<>(savedPieces, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving pieces",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Piece piece) {
        try {
            String msg = this.pieceService.update(piece, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error updating piece",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!pieceService.existsById(id)) {
                ApiError apiError = new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        "Piece not found",
                        "Piece with ID " + id + " does not exist"
                );
                return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
            }
            String msg = this.pieceService.delete(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting piece",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        try {
            this.pieceService.deleteAll();
            return new ResponseEntity<>("All pieces have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting all pieces",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAll() {
        try {
            List<Piece> pieces = this.pieceService.listAll();
            return new ResponseEntity<>(pieces, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error listing pieces",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Piece piece = this.pieceService.findById(id);
            return new ResponseEntity<>(piece, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Piece not found",
                    "Piece with ID " + id + " does not exist"
            );
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error finding piece",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }
}
