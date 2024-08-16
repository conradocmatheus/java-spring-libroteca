package app.library.controller;

import app.library.entity.PieceType;
import app.library.exception.ApiError;
import app.library.service.PieceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/pieceType")
public class PieceTypeController {

    @Autowired
    private PieceTypeService pieceTypeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PieceType pieceType) {
        try {
            String msg = this.pieceTypeService.save(pieceType);
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving PieceType",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<PieceType> pieceTypes) {
        try {
            List<PieceType> savedPieceTypes = this.pieceTypeService.saveAll(pieceTypes);
            return new ResponseEntity<>(savedPieceTypes, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving PieceTypes",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PieceType pieceType) {
        try {
            String msg = this.pieceTypeService.update(pieceType, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error updating PieceType",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!pieceTypeService.existsById(id)) {
                ApiError apiError = new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        "PieceType not found",
                        "PieceType with ID " + id + " does not exist"
                );
                return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
            }
            String msg = this.pieceTypeService.delete(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting PieceType",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        try {
            this.pieceTypeService.deleteAll();
            return new ResponseEntity<>("All PieceTypes have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting all PieceTypes",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAll() {
        try {
            List<PieceType> pieceTypes = this.pieceTypeService.listAll();
            return new ResponseEntity<>(pieceTypes, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error listing PieceTypes",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            PieceType pieceType = this.pieceTypeService.findById(id);
            return new ResponseEntity<>(pieceType, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "PieceType not found",
                    "PieceType with ID " + id + " does not exist"
            );
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error finding PieceType",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<?> existsById(@PathVariable Long id) {
        try {
            boolean exists = this.pieceTypeService.existsById(id);
            return new ResponseEntity<>(exists, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error checking existence of PieceType",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }
}