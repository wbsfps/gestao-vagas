package br.com.wbs.gestao_vagas.modules.candidate.useCases;

import br.com.wbs.gestao_vagas.exceptions.UserFoundException;
import br.com.wbs.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.wbs.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository repository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.repository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.repository.save(candidateEntity);
    }
}
