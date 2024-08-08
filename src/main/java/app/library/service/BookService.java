package app.library.service;

import app.library.entity.Book;
import app.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public String save(Book book){
        return "Book successfully saved!";
    }

    public Book findById(Long id){

        if (id == 1) {
            Book book = new Book();
            book.setName("IT");
            book.setAuthor("Stephen King");
            book.setEditor("Graciota");
            book.setYear(1995);
            return book;
        }
        else {
            return null;
        }
    }
}
