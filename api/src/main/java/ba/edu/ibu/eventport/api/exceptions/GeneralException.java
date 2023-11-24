package ba.edu.ibu.eventport.api.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException {

  private int httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

  public GeneralException(int httpCode) {
    this.httpCode = httpCode;
  }

  public GeneralException(int httpCode, String message) {
    super(message);
    this.httpCode = httpCode;
  }

  public GeneralException(String message, Exception e) {
    super(message, e);
  }

  public GeneralException(Exception e) {
    super(e);
  }

  public GeneralException(String message) {
    super(message);
  }
}
