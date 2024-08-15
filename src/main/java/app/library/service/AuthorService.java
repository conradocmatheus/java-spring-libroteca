package app.library.service;

import app.library.entity.Author;
import app.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Save an Author
    public String save(Author author){
        this.authorRepository.save(author);
        return "Author: " + author.getName() + ", successfully saved";
    }

    // Update an Author by ID
    public String update(Author author, Long id){
        author.setId(id);
        authorRepository.save(author);
        return (author.getName() + " successfully updated!");
    }

    // Delete an Author by ID
    public String delete(Long id){
        this.authorRepository.deleteById(id);
        return "Car with id: " + id + "deleted";
    }

    // List all Authors
    public List<Author> listAll(){
        return this.authorRepository.findAll();
    }

    // Find an Author by ID
    public Author findById(Long id){
        return this.authorRepository.findById(id).get();
    }
}
