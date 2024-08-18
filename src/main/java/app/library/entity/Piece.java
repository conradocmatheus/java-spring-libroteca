package app.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
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
    private String title;

    @Nullable
    private String description;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference// dont change
    private PieceType pieceType;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Genre genre;

    @ManyToOne
    @JsonIgnore
    private Sale sale;

    @Min(0)
    private Integer year;

    @Positive
    private Double value;

    @Min(0)
    private Integer pages;
}