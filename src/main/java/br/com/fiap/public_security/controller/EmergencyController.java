package br.com.fiap.public_security.controller;

import br.com.fiap.public_security.dto.EmergencyRegisterDto;
import br.com.fiap.public_security.dto.EmergencyResponseDto;
import br.com.fiap.public_security.dto.EmergencyUpdateDto;
import br.com.fiap.public_security.model.Emergency;
import br.com.fiap.public_security.service.EmergencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmergencyController {
    @Autowired
    private EmergencyService service;

    @GetMapping("/emergency/test")
    @ResponseStatus(HttpStatus.OK)
    public String test(){ return "Hello World!"; }

    @PostMapping("/emergency")
    @ResponseStatus(HttpStatus.CREATED)
    public EmergencyResponseDto register(
            @RequestBody @Valid EmergencyRegisterDto emergencyRegisterDto
    ){
        return service.register(emergencyRegisterDto);
    }

    @GetMapping("/emergency/by_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmergencyResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/emergency/by_email/{email}")
    public List<EmergencyResponseDto> findByRequestEmail(@PathVariable String email) {
        return service.findByRequestEmail(email);
    }
    @GetMapping("/emergency")
    @ResponseStatus(HttpStatus.OK)
    public Page<EmergencyResponseDto> listAll(Pageable pageable) {
        return service.listAll(pageable);
    }

    @GetMapping("/emergency/by_date/{start}/{final}")
    public List<EmergencyResponseDto> findByRequestDate(
           @PathVariable LocalDateTime dateTimeStart,
           @PathVariable LocalDateTime dateTimeFinal
    ){
        return service.findByRequestDate(dateTimeStart, dateTimeFinal);
    }

    @PutMapping("/emergency")
    @ResponseStatus(HttpStatus.OK)
    public EmergencyResponseDto update(@RequestBody EmergencyUpdateDto emergencyUpdateDto) {
        return service.update(emergencyUpdateDto);
    }

    @DeleteMapping("/emergency/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
