package fr.doandgo.gestionrh;

import fr.doandgo.gestionrh.ihm.StartInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final StartInterface startInterface;

    @Autowired
    public ApplicationRunner(StartInterface startInterface) {
        this.startInterface = startInterface;
    }

    @Override
    public void run(String... args) throws Exception {
        startInterface.startInterface();
    }
}