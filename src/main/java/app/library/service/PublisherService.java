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

    public String save(Publisher publisher){
        publisherRepository.save(publisher);
        return "Publisher successfully saved!";
    }

    public List<Publisher> listAll(){
        return this.publisherRepository.findAll();
    }

    public Publisher findById(Long id){
        return publisherRepository.findById(id).get();
    }

    public String update(Publisher publisher, Long id){
        publisher.setId(id);
        publisherRepository.save(publisher);
        return "Publisher successfully updated!";
    }

    public String delete(Long id){
        this.publisherRepository.deleteById(id);
        return "Publisher successfully deleted!";
    }
}
