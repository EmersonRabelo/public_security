package br.com.fiap.public_security.controller;

import br.com.fiap.public_security.dto.AlarmRegisterDto;
import br.com.fiap.public_security.dto.AlarmResponseDto;
import br.com.fiap.public_security.dto.AlarmUpdateDto;
import br.com.fiap.public_security.service.AlarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AlarmController {
    @Autowired
    private AlarmService service;

    @GetMapping("/alarm/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(){ return "Hello World!"; }

    @GetMapping("/alarm")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlarmResponseDto> listAll(Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping("/alarm")
    @ResponseStatus(HttpStatus.CREATED)
    public AlarmResponseDto register(
            @RequestBody @Valid AlarmRegisterDto alarmRegisterDto
    ){
        return service.register(alarmRegisterDto);
    }

    @PutMapping("/alarm")
    @ResponseStatus(HttpStatus.OK)
    public AlarmResponseDto update(
            @RequestBody AlarmUpdateDto alarmUpdateDto
    ){
        return service.update(alarmUpdateDto);
    }

    @DeleteMapping("/alarm/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
