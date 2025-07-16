package br.com.wbs.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCandidateResponseDTO {
    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "Mario")
    private String username;

    @Schema(example = "mario@gmail.com")
    private String email;

    private UUID id;

    @Schema(example = "Mario")
    private String name;
}
