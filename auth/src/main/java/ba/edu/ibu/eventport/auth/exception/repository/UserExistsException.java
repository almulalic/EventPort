package ba.edu.ibu.eventport.auth.exception.repository;

import ba.edu.ibu.eventport.auth.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistsException extends GeneralException {
  public UserExistsException() {
    super(HttpStatus.BAD_REQUEST.value());
  }

  public UserExistsException(String message) {
    super(HttpStatus.BAD_REQUEST.value(), message);
  }
}
