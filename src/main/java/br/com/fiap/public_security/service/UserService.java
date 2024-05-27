package br.com.fiap.public_security.service;

import br.com.fiap.public_security.dto.UserRegisterDto;
import br.com.fiap.public_security.dto.UserResponseDto;
import br.com.fiap.public_security.repository.UserRepository;
import br.com.fiap.public_security.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponseDto userRegister(UserRegisterDto userRegisterDto) {
        Optional<UserDetails> hasUser = Optional.ofNullable(repository.findByEmail(userRegisterDto.email()));
        if (hasUser.isPresent()){
            throw new DataIntegrityViolationException("Already registered user.");
        }

        String pwdEncrypted = new BCryptPasswordEncoder().encode(userRegisterDto.password());

        User user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        user.setPassword(pwdEncrypted);

        User registered = repository.save(user);
        return new UserResponseDto(registered);
    }
}
