package ba.edu.ibu.eventport.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EventPortAPIApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventPortAPIApplication.class, args);
  }

}
