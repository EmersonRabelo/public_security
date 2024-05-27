package br.com.fiap.public_security.service;

import br.com.fiap.public_security.dto.EmergencyRegisterDto;
import br.com.fiap.public_security.dto.EmergencyResponseDto;
import br.com.fiap.public_security.dto.EmergencyUpdateDto;
import br.com.fiap.public_security.exception.EmergencyNotFoundException;
import br.com.fiap.public_security.exception.UserNotFoundException;
import br.com.fiap.public_security.model.Emergency;
import br.com.fiap.public_security.repository.EmergencyRepository;
import br.com.fiap.public_security.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmergencyService {

    @Autowired
    private EmergencyRepository emergencyRepository;

    @Autowired
    private UserRepository userRepository;

    public EmergencyResponseDto register(EmergencyRegisterDto emergencyRegisterDto) {
        if (!findUserByEmail(emergencyRegisterDto.requesterEmail())){
            throw new UserNotFoundException("User not found.");
        } else {
            Emergency emergency = new Emergency();
            emergency.setRequestDate(LocalDateTime.now());
            BeanUtils.copyProperties(emergencyRegisterDto, emergency);
            return new EmergencyResponseDto(emergencyRepository.save(emergency));
        }
    }

    public List<EmergencyResponseDto> findByRequestDate(
            LocalDateTime dateTimeStart,
            LocalDateTime dateTimeFinal
    ) {
        return emergencyRepository.findByRequestDateBetween(dateTimeStart, dateTimeFinal);
    }

    public List<EmergencyResponseDto> findByRequestEmail(String email) {
        if (!findUserByEmail(email)) {
            throw new UserNotFoundException("User not found.");
        } else {
            return emergencyRepository.findByRequesterEmail(email);
        }
    }

    public EmergencyResponseDto findById(Long id) {
        Optional<Emergency> optionalEmergency = emergencyRepository.findById(id);
        if(optionalEmergency.isPresent()) {
            return new EmergencyResponseDto(optionalEmergency.get());
        } else {
            throw new EmergencyNotFoundException("Emergency Not Found.");
        }
    }

    public Page<EmergencyResponseDto> listAll(Pageable pageable) {
        return emergencyRepository.findAll(pageable).map(EmergencyResponseDto::new);
    }

    public EmergencyResponseDto update(EmergencyUpdateDto emergencyUpdateDto){
        Optional<Emergency> optionalEmergency = emergencyRepository.findById(emergencyUpdateDto.emergencyId());
        if (optionalEmergency.isPresent()) {
            Emergency emergency = new Emergency();
            emergency.setRequestDate(optionalEmergency.get().getRequestDate());
            BeanUtils.copyProperties(emergencyUpdateDto, emergency);
            return new EmergencyResponseDto(emergencyRepository.save(emergency));
        }else {
            throw new EmergencyNotFoundException("Emergency Not Found.");
        }
    }

    public void delete(Long id){
        Optional<Emergency> emergencyOptional = emergencyRepository.findById(id);
        if (emergencyOptional.isPresent()){
            emergencyRepository.delete(emergencyOptional.get());
        } else {
            throw new EmergencyNotFoundException("Emergency Not Found.");
        }
    }

    private Boolean findUserByEmail(String email) {
        Optional<UserDetails> optionalUserDetails = Optional.ofNullable(userRepository.findByEmail(email));
        return optionalUserDetails.isPresent();
    }

}
