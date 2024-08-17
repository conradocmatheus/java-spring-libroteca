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
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Employee employee;

    @NotNull
    @JsonIgnoreProperties("sale")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Client client;

    @NotNull
    @JsonIgnoreProperties("sale")
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Piece> pieces;

    @NotNull
    @Positive
    private Double totalValue;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate saleDate;

    private String paymentMethod;

    @NotBlank
    private String obs;
}

