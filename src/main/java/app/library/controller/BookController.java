package app.library.controller;

import app.library.entity.Book;
import app.library.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Book book){
        try {
            String message = this.bookService.save(book);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<String>("Could not save new book", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<Book>> listAll(){
        try{
            List<Book> lista = this.bookService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        try {
            Book book = this.bookService.findById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            String mensagem = this.bookService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
