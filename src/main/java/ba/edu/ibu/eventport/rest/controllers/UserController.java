package ba.edu.ibu.eventport.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.edu.ibu.eventport.rest.models.dto.UserDTO;
import ba.edu.ibu.eventport.core.service.UserService;
import ba.edu.ibu.eventport.rest.models.dto.UserRequestDTO;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/")
  public ResponseEntity<List<UserDTO>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public ResponseEntity<UserDTO> getUserById(
    @PathVariable String id
  ) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/identifier/{identifier}")
  public ResponseEntity<UserDTO> getUser(
    @PathVariable String identifier
  ) {
    return ResponseEntity.ok(userService.getUser(identifier));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/register")
  public ResponseEntity<UserDTO> register(
    @RequestBody UserRequestDTO user
  ) {
    return ResponseEntity.ok(userService.addUser(user));
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  public ResponseEntity<UserDTO> update(
    @PathVariable String id,
    @RequestBody UserRequestDTO user
  ) {
    return ResponseEntity.ok(userService.updateUser(id, user));
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public ResponseEntity<Void> deleteUser(
    @PathVariable String id
  ) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
