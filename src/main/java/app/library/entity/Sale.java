package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Nullable
    @JsonIgnoreProperties("sales")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employee employee;

    // when selling, if client age < 18, value must not pass 500$
    @NotNull
    @JsonIgnoreProperties("sale")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Client client;

    @NotBlank
    @JsonIgnoreProperties("sale")
    @OneToMany(mappedBy = "sale")
    private List<Piece> pieces;

    @NotNull
    @Positive
    private Double totalValue;

    @DateTimeFormat
    private LocalDate saleDate;

    private String paymentMethod;

    @NotNull
    private String obs;
}
