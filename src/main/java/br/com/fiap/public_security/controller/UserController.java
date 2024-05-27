package br.com.fiap.public_security.controller;

import br.com.fiap.public_security.dto.UserRegisterDto;
import br.com.fiap.public_security.dto.UserResponseDto;
import br.com.fiap.public_security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(){ return "Hello World!"; }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(
            @RequestBody @Valid UserRegisterDto userRegister
    ){
        return service.userRegister(userRegister);
    }
}
