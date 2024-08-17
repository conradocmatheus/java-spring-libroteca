package app.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Employee employee;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Client client;

    @NotNull
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Piece> pieces;

    @NotNull
    @Positive
    private Double totalValue;

    @NotNull
    private LocalDate saleDate;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String obs;
}
