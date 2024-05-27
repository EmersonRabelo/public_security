package br.com.fiap.public_security.controller;

import br.com.fiap.public_security.config.security.TokenService;
import br.com.fiap.public_security.dto.LoginDto;
import br.com.fiap.public_security.dto.TokenDto;
import br.com.fiap.public_security.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() { return "Hello World!"; }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()
                );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }
}
