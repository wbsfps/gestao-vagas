package br.com.wbs.gestao_vagas.modules.company.useCases;

import br.com.wbs.gestao_vagas.exceptions.UserFoundException;
import br.com.wbs.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.wbs.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.wbs.gestao_vagas.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new UserFoundException();
                });
        var password = securityConfig.passwordEncoder().encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
