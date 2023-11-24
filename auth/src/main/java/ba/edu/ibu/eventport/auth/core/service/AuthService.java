package ba.edu.ibu.eventport.auth.core.service;

import ba.edu.ibu.eventport.auth.core.model.User;
import ba.edu.ibu.eventport.auth.core.model.enums.AuthType;
import ba.edu.ibu.eventport.auth.core.model.enums.UserType;
import ba.edu.ibu.eventport.auth.core.repository.UserRepository;
import ba.edu.ibu.eventport.auth.exception.repository.UserExistsException;
import ba.edu.ibu.eventport.auth.exception.repository.UserNotFoundException;
import ba.edu.ibu.eventport.auth.rest.models.dto.CreateUserDTO;
import ba.edu.ibu.eventport.auth.rest.models.dto.GenerateTokenDTO;
import ba.edu.ibu.eventport.auth.rest.models.dto.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  @Autowired

  public AuthService(
    PasswordEncoder passwordEncoder,
    JWTService jwtService,
    AuthenticationManager authenticationManager,
    UserRepository userRepository
  ) {
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }

  public User createUser(CreateUserDTO dto) {
    if (this.userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new UserExistsException("User with this email already exists");
    }

    User user = dto.toEntity();
    user.setAuthType(AuthType.PLAIN);
    user.setUserType(UserType.GUEST);
    user.setPassword(passwordEncoder.encode(dto.getPassword()));

    return this.userRepository.save(user);
  }

  public TokenResponseDTO generateToken(GenerateTokenDTO dto) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
    );

    User user = userRepository
                  .findByEmail(dto.getEmail())
                  .orElseThrow(() -> new UserNotFoundException(
                    "User with email '%s' not found.".formatted(dto.getEmail())
                  ));

    return new TokenResponseDTO.Builder()
             .withUser(user)
             .withToken(jwtService.generateToken(user))
             .build();
  }
}
