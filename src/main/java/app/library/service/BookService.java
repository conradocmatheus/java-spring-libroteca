package app.library.service;

import app.library.entity.Book;
import app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public String save(Book book){
        bookRepository.save(book);
        return "Book successfully saved!";
    }
    public List<Book> listAll(){
        return this.bookRepository.findAll();
    }
    public Book findById(Long id){
        return bookRepository.findById(id).get();
    }
    public String updateBook(Book book, Long id){
        book.setId(id);
        bookRepository.save(book);
        return "Book successfully updated!";
    }
    public String delete(Long id){
        this.bookRepository.deleteById(id);
        return "Book successfully deleted!";
    }

}
