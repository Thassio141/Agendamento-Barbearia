package br.com.haircutappoitment.controllers;

import br.com.haircutappoitment.domain.dtos.auth.AuthenticationDto;
import br.com.haircutappoitment.domain.dtos.auth.LoginResponseDto;
import br.com.haircutappoitment.domain.dtos.user.UserCreateDto;
import br.com.haircutappoitment.domain.entities.UserEntity;
import br.com.haircutappoitment.domain.enums.ActivityStatus;
import br.com.haircutappoitment.repositories.UserRepository;
import br.com.haircutappoitment.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(),data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserCreateDto userCreateDto){
        if(this.userRepository.findByEmail(userCreateDto.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userCreateDto.getPassword());

        UserEntity user = new UserEntity(userCreateDto.getName(),userCreateDto.getEmail(),encryptedPassword,userCreateDto.getUserRole(),ActivityStatus.ACTIVE);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
