package com.ishingarov.catus.controller;

import com.ishingarov.catus.dto.LoginRequestDto;
import com.ishingarov.catus.dto.RegisterRequestDto;
import com.ishingarov.catus.dto.TokenResponse;
import com.ishingarov.catus.dto.user.UserMapper;
import com.ishingarov.catus.service.TokenService;
import com.ishingarov.catus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Получение токена авторизации")
    @PostMapping("/login")
    public TokenResponse loginHandler(@RequestBody LoginRequestDto request) {
        log.trace("Got request on /api/v1/login");
        log.debug("Got login request for '{}'", request.login());
        log.trace("Request body: {}", request);
        var token = getTokenByLoginAndPassword(request.login(), request.password());
        log.trace("Generated token: {}", token);
        return new TokenResponse(token);
    }

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/register")
    public TokenResponse registrationHandler(@RequestBody RegisterRequestDto request) {
        log.trace("Got request on /api/v1/register");
        log.debug("Got registration request for '{}'", request.login());
        log.trace("Request body: {}", request);
        var user = userService.createUser(
                userMapper.mapRequestOnModel(request));
        var token = getTokenByLoginAndPassword(request.login(), request.password());
        log.trace("Generated token: {}", token);
        return new TokenResponse(token);
    }

    @Operation(summary = "Обновление токена аутентификации", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/token/refresh")
    @PreAuthorize("isAuthenticated()")
    public TokenResponse refreshToken(Authentication authentication) {
        return new TokenResponse(tokenService.generateToken(authentication));
    }

    private String getTokenByLoginAndPassword(String login, String password) {
        var auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login, password));
        return tokenService.generateToken(auth);
    }

}
