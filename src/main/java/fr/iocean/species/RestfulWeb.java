package fr.iocean.species;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RestfulWeb {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWeb.class, args);
    }


}
