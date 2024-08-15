package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PieceType pieceType;

    @NotNull
    @JsonIgnoreProperties("pieces")
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Author author;

    @NotNull
    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @NotNull
    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Genre genre;

    @NotNull
    @JsonIgnoreProperties("pieces")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Sale sale;

    @Min(0)
    @Positive
    private Integer year;

    @Positive
    private Double value;

    @Min(0)
    private Integer pages;
}
