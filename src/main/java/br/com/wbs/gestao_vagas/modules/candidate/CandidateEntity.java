package br.com.wbs.gestao_vagas.modules.candidate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "candidate")
@Data
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O [username] não deve conter espaço")
    private String username;

    @Email(message = "O campo [email] teve ter um email válido")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve ter entre (10) e (100) caracteres")
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDate createdAt;
}
