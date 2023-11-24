package ba.edu.ibu.eventport.api.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class APIRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIRegistryApplication.class, args);
	}

}
