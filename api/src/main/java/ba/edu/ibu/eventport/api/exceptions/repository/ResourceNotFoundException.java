package ba.edu.ibu.eventport.api.exceptions.repository;

import ba.edu.ibu.eventport.api.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GeneralException {
  public ResourceNotFoundException() {
    super(HttpStatus.NOT_FOUND.value());
  }

  public ResourceNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND.value(), message);
  }
}