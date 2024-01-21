package ba.edu.ibu.eventport.api.rest.configurations.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class JwtExpiredExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
    value = {
      ExpiredJwtException.class,
      AuthenticationException.class
    }
  )
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  protected ResponseEntity<Object> handleExpiredJwt(Exception ex, WebRequest request) {
    String bodyOfResponse = "Unauthorized: JWT token expired";
    return handleExceptionInternal(
      ex,
      bodyOfResponse,
      HttpHeaders.EMPTY,
      HttpStatus.UNAUTHORIZED,
      request
    );
  }
}