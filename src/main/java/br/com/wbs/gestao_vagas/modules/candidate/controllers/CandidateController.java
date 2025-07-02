package br.com.wbs.gestao_vagas.modules.candidate.controllers;

import br.com.wbs.gestao_vagas.exceptions.UserFoundException;
import br.com.wbs.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.wbs.gestao_vagas.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @PostMapping
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        this.repository
                .findByUsernameOrEmail
                        (candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.repository.save(candidateEntity);
    }
}
