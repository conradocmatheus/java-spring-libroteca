package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("sales")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employee employee;

    @JsonIgnoreProperties("sale")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Client client;

    @JsonIgnoreProperties("sale")
    @OneToMany(mappedBy = "sale")
    private List<Piece> pieces;

    @NotNull
    private Double totalValue;
}
