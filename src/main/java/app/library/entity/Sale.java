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
    @ManyToOne
    @JsonBackReference
    private Employee employee;

    @NotNull
    @ManyToOne
    @JsonBackReference("client-sales")
    private Client client;

    @NotNull
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
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

