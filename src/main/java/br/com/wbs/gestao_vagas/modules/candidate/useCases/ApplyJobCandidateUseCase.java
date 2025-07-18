package br.com.wbs.gestao_vagas.modules.candidate.useCases;

import br.com.wbs.gestao_vagas.exceptions.JobNotFoundException;
import br.com.wbs.gestao_vagas.exceptions.UserNotFoundException;
import br.com.wbs.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.wbs.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.wbs.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplyJobRepository applyJobRepository;

    // Id do Candidato
    // Id da Vaga
    public void execute(UUID idCandidate, UUID idJob) {
        // Validar se o candidato existe
        this.candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);

        // Validar se a vaga existe
        this.jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

        // Candidato se inscrever da vaga
    }
}
