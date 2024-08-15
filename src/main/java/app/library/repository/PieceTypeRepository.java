package app.library.repository;

import app.library.entity.PieceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceTypeRepository extends JpaRepository<PieceType, Long> {
}
