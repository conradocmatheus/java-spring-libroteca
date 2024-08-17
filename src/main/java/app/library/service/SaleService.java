package app.library.service;

import app.library.entity.Sale;
import app.library.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    // Save a single Sale
    public String save(Sale sale) {
        saleRepository.save(sale);
        return "Sale successfully saved";
    }

    // Save a list of Sales
    public List<Sale> saveAll(List<Sale> sales) {
        return saleRepository.saveAll(sales);
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
