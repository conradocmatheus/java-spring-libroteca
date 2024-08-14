package app.library.controller;

import app.library.entity.Piece;
import app.library.service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Piece piece){
        try {
            String message = this.pieceService.save(piece);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Could not save new piece", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<Piece>> listAll(){
        try{
            List<Piece> list = this.pieceService.listAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Piece> findById(@PathVariable Long id){
        try {
            Piece piece = this.pieceService.findById(id);
            return new ResponseEntity<>(piece, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Piece piece, @PathVariable Long id){
        try {
            String message = this.pieceService.save(piece);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Could not update piece", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            String message = this.pieceService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
