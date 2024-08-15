package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


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

    @Positive
    private Integer age;

    @Email
    private String email;

    @Nullable
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Phone number format")
    private String phone;

    @CPF
    private String cpf;

    @Nullable
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Not valid")
    private String cep;

    @JsonIgnoreProperties
    @OneToOne(mappedBy = "client")
    private Sale sale;
}
