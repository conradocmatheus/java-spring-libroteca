package app.library.service;

import app.library.entity.Client;
import app.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Save a Client
    public String save(Client client) {
        clientRepository.saveAndFlush(client); // Use saveAndFlush for persistence
        return "Client: " + client.getName() + ", successfully saved";
    }

    // Update a Client by ID
    public String update(Client client, Long id) {
        client.setId(id);
        clientRepository.saveAndFlush(client); // Use saveAndFlush for persistence
        return client.getName() + " successfully updated!";
    }

    // Delete a Client by ID
    public String delete(Long id) {
        clientRepository.deleteById(id);
        return "Client with id: " + id + " deleted";
    }

    // List all Clients
    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    // Find a Client by ID
    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }
}
