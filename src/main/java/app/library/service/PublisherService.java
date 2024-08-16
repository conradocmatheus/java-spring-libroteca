package app.library.service;

import app.library.entity.Publisher;
import app.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    // Save a single Publisher
    public String save(Publisher publisher) {
        publisherRepository.save(publisher);
        return "Publisher: " + publisher.getName() + " successfully saved";
    }

    // Save a list of Publishers
    public List<Publisher> saveAll(List<Publisher> publishers) {
        return publisherRepository.saveAll(publishers);
    }

    // Update a Publisher by ID
    public String update(Publisher publisher, Long id) {
        publisher.setId(id);
        publisherRepository.save(publisher);
        return "Publisher: " + publisher.getName() + " successfully updated!";
    }

    // Delete a Publisher by ID
    public String delete(Long id) {
        publisherRepository.deleteById(id);
        return "Publisher with id: " + id + " deleted";
    }

    // Delete all Publishers
    public void deleteAll() {
        publisherRepository.deleteAll();
    }

    // List all Publishers
    public List<Publisher> listAll() {
        return publisherRepository.findAll();
    }

    // Find a Publisher by ID
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElseThrow();
    }

    // Verify Publisher existence by ID
    public boolean existsById(Long id) {
        return publisherRepository.existsById(id);
    }
}
