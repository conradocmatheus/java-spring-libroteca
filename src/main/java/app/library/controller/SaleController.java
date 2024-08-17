package app.library.controller;

import app.library.entity.Sale;
import app.library.exception.ApiError;
import app.library.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Sale sale) {
        System.out.println("Entro aq");
        try {
            System.out.println("Entro aq tbm");
            Sale savedSale = this.saleService.save(sale);
            return new ResponseEntity<>(savedSale.toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving Sale",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<Sale> sales) {
        try {
            List<Sale> savedSales = this.saleService.saveAll(sales);
            return new ResponseEntity<>(savedSales, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error saving Sales",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Sale sale) {
        try {
            String msg = this.saleService.update(sale, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error updating Sale",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!saleService.existsById(id)) {
                ApiError apiError = new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        "Sale not found",
                        "Sale with ID " + id + " does not exist"
                );
                return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
            }
            String msg = this.saleService.delete(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting Sale",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        try {
            this.saleService.deleteAll();
            return new ResponseEntity<>("All Sales have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error deleting all Sales",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> listAll() {
        try {
            List<Sale> sales = this.saleService.listAll();
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error listing Sales",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Sale sale = this.saleService.findById(id);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiError apiError = new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    "Sale not found",
                    "Sale with ID " + id + " does not exist"
            );
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error finding Sale",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<?> existsById(@PathVariable Long id) {
        try {
            boolean exists = this.saleService.existsById(id);
            return new ResponseEntity<>(exists, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "Error checking existence of Sale",
                    e.getMessage()
            );
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }
}
