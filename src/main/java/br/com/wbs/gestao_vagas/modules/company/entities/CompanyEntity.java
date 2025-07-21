package br.com.wbs.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O [username] não deve conter espaço")
    private String username;

    @Email(message = "O campo [email] teve ter um email válido")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve ter entre (10) e (100) caracteres")
    private String password;
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
