package app.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY) // Change to ManyToOne to allow repeated client entries
    @JsonBackReference("client-sales")
    private Client client;

    @NotNull
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Piece> pieces;

    @NotNull
    @Positive
    private Double totalValue;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date localDate;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String obs;
}

