package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    @JsonIgnoreProperties
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Author author;

    @JsonIgnoreProperties
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;

    private Integer year;

    private Double value;

    private Integer pages;
}
