package ba.edu.ibu.eventport.auth.exception.repository;

import ba.edu.ibu.eventport.auth.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends GeneralException {
  public UserNotFoundException() {
    super(HttpStatus.NOT_FOUND.value());
  }

  public UserNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND.value(), message);
  }
}

