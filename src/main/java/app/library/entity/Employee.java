package app.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private Integer age;

    @Nullable
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$", message = "Phone number format")
    private String phone;

    @Email
    private String email;

    @JsonIgnoreProperties("employee")
    @OneToMany(mappedBy = "employee")
    private List<Sale> sales;
}
