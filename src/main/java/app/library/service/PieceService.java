package app.library.service;

import app.library.entity.Piece;
import app.library.repository.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    // Save a single Piece
    public String save(Piece piece) {
        pieceRepository.save(piece);
        return "Piece: " + piece.getTitle() + ", successfully saved";
    }

    // Save a list of Pieces
    public List<Piece> saveAll(List<Piece> pieces) {
        return pieceRepository.saveAll(pieces);
    }

    // Update a Piece by ID
    public String update(Piece piece, Long id) {
        piece.setId(id);
        pieceRepository.save(piece);
        return "Piece: " + piece.getTitle() + " successfully updated!";
    }

    // Delete a Piece by ID
    public String delete(Long id) {
        pieceRepository.deleteById(id);
        return "Piece with id: " + id + " deleted";
    }

    // Delete all Pieces
    public void deleteAll() {
        pieceRepository.deleteAll();
    }

    // List all Pieces
    public List<Piece> listAll() {
        return pieceRepository.findAll();
    }

    // Find a Piece by ID
    public Piece findById(Long id) {
        return pieceRepository.findById(id).orElseThrow();
    }

    // Verify Piece existence by ID
    public boolean existsById(Long id) {
        return pieceRepository.existsById(id);
    }
}
