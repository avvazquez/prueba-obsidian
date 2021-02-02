package com.pruebaobsidian.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaObsidianBackendApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PruebaObsidianBackendApplication.class, args);

        //PruebaWebClient pwc = new PruebaWebClient();
        //pwc.getGetFlux().subscribe();
        //pwc.getPostMono().subscribe();
        //pwc.getTrees().subscribe();
    }
}
