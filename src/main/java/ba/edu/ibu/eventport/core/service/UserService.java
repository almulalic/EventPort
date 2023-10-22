package ba.edu.ibu.eventport.core.service;

import ba.edu.ibu.eventport.core.model.User;
import ba.edu.ibu.eventport.rest.models.dto.UserRequestDTO;
import org.springframework.stereotype.Service;
import ba.edu.ibu.eventport.rest.models.dto.UserDTO;
import ba.edu.ibu.eventport.core.repository.UserRepository;
import ba.edu.ibu.eventport.exceptions.repository.ResourceNotFoundException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserDTO> getUsers() {
    List<User> users = userRepository.findAll();

    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }

  public UserDTO getUserById(String id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      throw new ResourceNotFoundException("The user with the given ID does not exists.");
    }

    return new UserDTO(user.get());
  }

  public UserDTO getUser(String email, String username) {
    Optional<User> user = userRepository.findByEmailOrUsername(email, username);

    if (user.isEmpty()) {
      throw new ResourceNotFoundException("The user with the given ID does not exists.");
    }

    return new UserDTO(user.get());
  }

  public UserDTO addUser(UserRequestDTO payload) {
    User user = userRepository.save(payload.toEntity());
    return new UserDTO(user);
  }

  public UserDTO updateUser(String id, UserRequestDTO payload) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      throw new ResourceNotFoundException("The user with the given ID does not exists.");
    }

    User updatedUser = payload.toEntity();
    updatedUser.setId(id);
    updatedUser = userRepository.save(updatedUser);
    return new UserDTO(updatedUser);
  }

  public void deleteUser(String id) {
    Optional<User> user = userRepository.findById(id);
    user.ifPresent(userRepository::delete);
  }
}
