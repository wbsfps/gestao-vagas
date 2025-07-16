package br.com.wbs.gestao_vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
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

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "candidate")
@Data
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Daniel", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O [username] não deve conter espaço")
    @Schema(example = "daniel", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @Email(message = "O campo [email] teve ter um email válido")
    @Schema(example = "daniel@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "Email do candidato")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve ter entre (10) e (100) caracteres")
    @Schema(example = "admin@1234", maxLength = 100, minLength = 10, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;
    @Schema(example = "Desenvolvedor java", requiredMode = RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
