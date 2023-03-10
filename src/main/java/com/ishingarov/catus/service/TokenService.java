package com.ishingarov.catus.service;

import com.ishingarov.catus.model.UserRole;
import com.ishingarov.catus.model.domain.UserModel;
import com.ishingarov.catus.model.domain.UserModelMapper;
import com.ishingarov.catus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;
    private final AuthenticationManager authenticationManager;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getTokenByLoginAndPassword(String login, String password) {
        var auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login, password));
        return generateToken(auth);
    }

    public String refreshToken(Authentication authentication, UserRole role) {
        Instant now = Instant.now();
        return jwtEncoder.encode(JwtEncoderParameters.from(JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(now)
                        .expiresAt(now.plus(1, ChronoUnit.DAYS))
                        .subject(authentication.getName())
                        .claim("scope", role.toString())
                        .build()))
                .getTokenValue();
    }

    public UserModel getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userModelMapper.toModel(userRepository
                .findByLogin(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + principal.getSubject())));
    }

}
