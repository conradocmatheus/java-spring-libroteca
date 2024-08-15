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

    public String save(PieceType pieceType){
        pieceTypeRepository.save(pieceType);
        return "PieceType successfully saved!";
    }

    public List<PieceType> listAll(){
        return this.pieceTypeRepository.findAll();
    }

    public PieceType findById(Long id){
        return pieceTypeRepository.findById(id).get();
    }

    public String update(PieceType pieceType, Long id){
        pieceType.setId(id);
        pieceTypeRepository.save(pieceType);
        return "PieceType successfully updated!";
    }

    public String delete(Long id){
        this.pieceTypeRepository.deleteById(id);
        return "PieceType successfully deleted!";
    }
}
