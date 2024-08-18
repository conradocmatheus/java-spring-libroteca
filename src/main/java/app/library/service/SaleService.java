package app.library.service;

import app.library.entity.Client;
import app.library.entity.Employee;
import app.library.entity.Piece;
import app.library.entity.Sale;
import app.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PieceRepository pieceRepository;

    // Save a single Sale
    public Sale save(Sale sale) {
        try {
            if (sale.getClient() == null || sale.getClient().getId() == null) {
                throw new IllegalArgumentException("Client or Client ID not provided");
            }
            Client client = clientRepository.findById(sale.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            sale.setClient(client);

            if (sale.getEmployee() != null && sale.getEmployee().getId() != null) {
                Employee employee = employeeRepository.findById(sale.getEmployee().getId())
                        .orElseThrow(() -> new RuntimeException("Employee not found"));
                sale.setEmployee(employee);
            }

            if (sale.getPieces() == null || sale.getPieces().isEmpty()) {
                throw new IllegalArgumentException("Sale must contain at least one product");
            }

            if (sale.getClient().getAge() < 18 && sale.getTotalValue() > 500) {
                throw new IllegalArgumentException("For underage clients, the maximum value is 500$");
            }

            double total = 0.0;
            List<Piece> updatedPieceList = new ArrayList<>();
            for (Piece piece : sale.getPieces()) {
                Piece pieceFound = pieceRepository.findById(piece.getId())
                        .orElseThrow(() -> new RuntimeException("Product with ID " + piece.getId() + " not found"));
                updatedPieceList.add(pieceFound);
                total += pieceFound.getValue();
            }
            sale.setPieces(updatedPieceList);
            sale.setTotalValue(total);

            return saleRepository.save(sale);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // Update a Sale by ID
    public String update(Sale sale, Long id) {
        sale.setId(id);
        saleRepository.save(sale);
        return "Sale successfully updated!";
    }

    // Delete a Sale by ID
    public String delete(Long id) {
        saleRepository.deleteById(id);
        return "Sale with id: " + id + " deleted";
    }

    // Delete all Sales
    public void deleteAll() {
        saleRepository.deleteAll();
    }

    // List all Sales
    public List<Sale> listAll() {
        return saleRepository.findAll();
    }

    // Find a Sale by ID
    public Sale findById(Long id) {
        return saleRepository.findById(id).orElseThrow();
    }

    // Verify Sale existence by ID
    public boolean existsById(Long id) {
        return saleRepository.existsById(id);
    }
}
