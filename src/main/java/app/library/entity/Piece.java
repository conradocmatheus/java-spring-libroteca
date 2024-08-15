package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
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
