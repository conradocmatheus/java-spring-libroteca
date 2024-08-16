package app.library.service;

import app.library.entity.PieceType;
import app.library.repository.PieceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceTypeService {

    @Autowired
    private PieceTypeRepository pieceTypeRepository;

    // Save a single PieceType
    public String save(PieceType pieceType) {
        pieceTypeRepository.save(pieceType);
        return "PieceType: " + pieceType.getName() + " successfully saved";
    }

    // Save a list of PieceTypes
    public List<PieceType> saveAll(List<PieceType> pieceTypes) {
        return pieceTypeRepository.saveAll(pieceTypes);
    }

    // Update a PieceType by ID
    public String update(PieceType pieceType, Long id) {
        pieceType.setId(id);
        pieceTypeRepository.save(pieceType);
        return "PieceType: " + pieceType.getName() + " successfully updated!";
    }

    // Delete a PieceType by ID
    public String delete(Long id) {
        pieceTypeRepository.deleteById(id);
        return "PieceType with id: " + id + " deleted";
    }

    // Delete all PieceTypes
    public void deleteAll() {
        pieceTypeRepository.deleteAll();
    }

    // List all PieceTypes
    public List<PieceType> listAll() {
        return pieceTypeRepository.findAll();
    }

    // Find a PieceType by ID
    public PieceType findById(Long id) {
        return pieceTypeRepository.findById(id).orElseThrow();
    }

    // Verify PieceType existence by ID
    public boolean existsById(Long id) {
        return pieceTypeRepository.existsById(id);
    }
}
