package ba.edu.ibu.eventport.auth.core.service;

import ba.edu.ibu.eventport.auth.core.model.User;
import ba.edu.ibu.eventport.auth.rest.models.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private PasswordEncoder passwordEncoder;
  private JWTService jwtService;
  private AuthenticationManager authenticationManager;
  private UserService userService;

  @Autowired

  public AuthService(
    PasswordEncoder passwordEncoder,
    JWTService jwtService,
    AuthenticationManager authenticationManager,
    UserService userService
  ) {
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  public UserRequestDTO register(UserRequestDTO loginRequestDTO) {
    this.userService.addUser(loginRequestDTO);
    return loginRequestDTO;
  }

  public String signIn(UserRequestDTO loginRequestDTO) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
    );
    User user = userService.getUser(loginRequestDTO.getUsername());

    String jwt = jwtService.generateToken(user);


    return jwt;
  }

}
