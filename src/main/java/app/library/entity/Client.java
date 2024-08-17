package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\w+\\s\\w+", message = "Name must contain 2 words and 1 space character")
    private String name;

    @NotNull
    @Positive
    private Integer age;

    @NotBlank
    @Email
    private String email;

    @Nullable
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Phone number format should be (XX) XXXXX-XXXX")
    private String phone;

    @NotBlank
    @CPF
    private String cpf;

    @Nullable
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP format should be XXXXX-XXX")
    private String cep;

    @NotBlank
    private String homeAddress;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("client-sales")
    private List<Sale> sales;
}
