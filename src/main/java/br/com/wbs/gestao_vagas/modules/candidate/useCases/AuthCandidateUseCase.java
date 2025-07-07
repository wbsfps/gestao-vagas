package br.com.wbs.gestao_vagas.modules.candidate.useCases;

import br.com.wbs.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.wbs.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.wbs.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
       var candidate = this.candidateRepository
                .findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect!"));

       var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
       if (!passwordMatches) {
            throw new AuthenticationException();
       }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

       var token = JWT.create()
               .withIssuer("javagas")
               .withSubject(candidate.getId().toString())
               .withClaim("roles", Arrays.asList("candidate"))
               .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
               .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).build();
        return authCandidateResponse;
    }
}
