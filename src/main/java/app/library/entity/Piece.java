package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PieceType pieceType;

    @JsonIgnoreProperties("pieces")
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Author author;

    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Genre genre;

    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Sale sale;

    private Integer year;

    private Double value;

    private Integer pages;
}
