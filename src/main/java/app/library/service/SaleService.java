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

    public String save(Sale sale){
        saleRepository.save(sale);
        return "Sale successfully saved!";
    }

    public List<Sale> listAll(){
        return this.saleRepository.findAll();
    }

    public Sale findById(Long id){
        return saleRepository.findById(id).orElse(null);
    }

    public String update(Sale sale, Long id){
        sale.setId(id);
        saleRepository.save(sale);
        return "Sale successfully updated!";
    }

    public String delete(Long id){
        this.saleRepository.deleteById(id);
        return "Sale successfully deleted!";
    }
}
