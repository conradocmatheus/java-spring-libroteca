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

    public String save(Piece piece){
        pieceRepository.save(piece);
        return "Piece successfully saved!";
    }
    public List<Piece> listAll(){
        return this.pieceRepository.findAll();
    }
    public Piece findById(Long id){
        return pieceRepository.findById(id).get();
    }
    public String updateBook(Piece piece, Long id){
        piece.setId(id);
        pieceRepository.save(piece);
        return "Piece successfully updated!";
    }
    public String delete(Long id){
        this.pieceRepository.deleteById(id);
        return "Piece successfully deleted!";
    }
}
