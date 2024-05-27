package br.com.fiap.public_security.service;

import br.com.fiap.public_security.dto.AlarmRegisterDto;
import br.com.fiap.public_security.dto.AlarmResponseDto;
import br.com.fiap.public_security.dto.AlarmUpdateDto;
import br.com.fiap.public_security.exception.AlarmNotFoundException;
import br.com.fiap.public_security.model.Alarm;
import br.com.fiap.public_security.repository.AlarmRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository repository;

    public AlarmResponseDto register(AlarmRegisterDto alarmRegisterDto){
        Alarm alarm = new Alarm();
        alarm.setAlarmDate(LocalDateTime.now());
        BeanUtils.copyProperties(alarmRegisterDto, alarm);

        return new AlarmResponseDto(repository.save(alarm));
    }

    public Alarm findById(Long id) {
        Optional<Alarm> optionalAlarm = repository.findById(id);
        if (optionalAlarm.isPresent()){
            return optionalAlarm.get();
        } else {
            throw new AlarmNotFoundException("Alarm Not Found.");
        }
    }

    public Page<AlarmResponseDto> listAll(Pageable pageable){
        return repository
                .findAll(pageable)
                .map(AlarmResponseDto::new);
    }

    public AlarmResponseDto update(AlarmUpdateDto alarmUpdateDto){
        Optional<Alarm> optionalAlarm = repository.findById(alarmUpdateDto.alarmId());
        if (optionalAlarm.isPresent()) {
            Alarm alarm = new Alarm();
            alarm.setAlarmDate(optionalAlarm.get().getAlarmDate());
            BeanUtils.copyProperties(alarmUpdateDto, alarm);
            return new AlarmResponseDto(repository.save(alarm));
        }else {
            throw new AlarmNotFoundException("Alarm Not Found.");
        }
    }

    public void delete(Long id){
        Optional<Alarm> optionalAlarm = repository.findById(id);
        if (optionalAlarm.isPresent()){
            repository.delete(optionalAlarm.get());
        } else {
            throw new AlarmNotFoundException("Alarm Not Found.");
        }
    }
}
