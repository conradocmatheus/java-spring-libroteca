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
        bookRepository.save(book);
        return "Book successfully saved!";
    }

    public Book findById(Long id){
        return bookRepository.findById(id).get();
    }
}
