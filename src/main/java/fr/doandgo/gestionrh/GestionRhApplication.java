package fr.doandgo.gestionrh;

import fr.doandgo.gestionrh.ihm.StartInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionRhApplication.class, args);
	}

}
