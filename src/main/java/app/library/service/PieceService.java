package app.library.service;

import app.library.entity.*;
import app.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private PieceTypeRepository pieceTypeRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Save piece
    public Piece save(Piece piece) {
        try {
            if (piece.getPieceType() == null || piece.getPieceType().getId() == null) {
                throw new IllegalArgumentException("PieceType or PieceType ID not Provided");
            }
            PieceType pieceType = pieceTypeRepository.findById(piece.getPieceType().getId())
                    .orElseThrow(() -> new RuntimeException("PieceType not found"));
            piece.setPieceType(pieceType);

            if (piece.getAuthor() == null || piece.getAuthor().getId() == null) {
                throw new IllegalArgumentException("Author or Author ID not Provided");
            }
            Author author = authorRepository.findById(piece.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            piece.setAuthor(author);

            if (piece.getPublisher() == null || piece.getPublisher().getId() == null) {
                throw new IllegalArgumentException("Publisher or Publisher ID not Provided");
            }
            Publisher publisher = publisherRepository.findById(piece.getPublisher().getId())
                    .orElseThrow(() -> new RuntimeException("Publisher not found"));
            piece.setPublisher(publisher);

            if (piece.getGenre() == null || piece.getGenre().getId() == null) {
                throw new IllegalArgumentException("Genre or Genre ID not Provided");
            }
            Genre genre = genreRepository.findById(piece.getGenre().getId())
                    .orElseThrow(() -> new RuntimeException("Genre not found"));
            piece.setGenre(genre);

            // Save the piece and return it
            return pieceRepository.save(piece);
        } catch (Exception e) {
            // Log the error message for debugging purposes
            System.err.println("Error saving piece: " + e.getMessage());
            // Optionally, rethrow or handle the exception based on your application's needs
            throw new RuntimeException("Failed to save piece", e);
        }
    }

    // Update a Piece by ID
    public String update(Piece piece, Long id) {
        try {
            Piece existingPiece = pieceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Piece not found with ID: " + id));

            if (piece.getPieceType() == null || piece.getPieceType().getId() == null) {
                throw new IllegalArgumentException("PieceType or PieceType ID not Provided");
            }
            PieceType pieceType = pieceTypeRepository.findById(piece.getPieceType().getId())
                    .orElseThrow(() -> new RuntimeException("PieceType not found"));
            existingPiece.setPieceType(pieceType);

            if (piece.getAuthor() == null || piece.getAuthor().getId() == null) {
                throw new IllegalArgumentException("Author or Author ID not Provided");
            }
            Author author = authorRepository.findById(piece.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            existingPiece.setAuthor(author);

            if (piece.getPublisher() == null || piece.getPublisher().getId() == null) {
                throw new IllegalArgumentException("Publisher or Publisher ID not Provided");
            }
            Publisher publisher = publisherRepository.findById(piece.getPublisher().getId())
                    .orElseThrow(() -> new RuntimeException("Publisher not found"));
            existingPiece.setPublisher(publisher);

            if (piece.getGenre() == null || piece.getGenre().getId() == null) {
                throw new IllegalArgumentException("Genre or Genre ID not Provided");
            }
            Genre genre = genreRepository.findById(piece.getGenre().getId())
                    .orElseThrow(() -> new RuntimeException("Genre not found"));
            existingPiece.setGenre(genre);

            // Set other fields from the input piece to the existingPiece as needed
            existingPiece.setTitle(piece.getTitle());
            existingPiece.setDescription(piece.getDescription());
            // Add any other fields that need to be updated

            // Save the updated piece
            pieceRepository.save(existingPiece);

            return "Piece: " + existingPiece.getTitle() + " successfully updated!";
        } catch (Exception e) {
            // Log the error message for debugging purposes
            System.err.println("Error updating piece: " + e.getMessage());
            throw new RuntimeException("Failed to update piece", e);
        }
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
