package fr.doandgo.gestionrh.utils;

import org.springframework.stereotype.Component;

@Component
public class Stylized4LText {
    public void employee(){
        System.out.println("________________________________________________________________________________________");
        System.out.println("  ___  __  __  ___  _     ___ __   __ ___  ___  ___   __________________________________");
        System.out.println(" | __||  \\/  || _ \\| |   / _ \\\\ \\ / /|_ _|| __|/ __|");
        System.out.println(" | _| | |\\/| ||  _/| |__| (_) |\\ V /  | | | _| \\__ \\            ******* Salariés *******");
        System.out.println(" |___||_|  |_||_|  |____|\\___/  |_|  |___||___||___/");
    }

    public void contract(){
        System.out.println("________________________________________________________________________________________");
        System.out.println("   ___  ___   _  _  _____  ___    _    ___  _____   ____________________________________");
        System.out.println("  / __|/ _ \\ | \\| ||_   _|| _ \\  /_\\  / __||_   _|");
        System.out.println(" | (__| (_) || .` |  | |  |   / / _ \\| (__   | |                ******* Contrats *******");
        System.out.println("  \\___|\\___/ |_|\\_|  |_|  |_|_\\/_/ \\_\\\\___|  |_|  ");
    }

    public void job(){
        System.out.println("________________________________________________________________________________________");
        System.out.println("     _   ___   ___  ___    _____________________________________________________________");
        System.out.println("  _ | | / _ \\ | _ )/ __|");
        System.out.println(" | || || (_) || _ \\__ \\                                  ******* Fiches de poste *******");
        System.out.println("  \\__/  \\___/ |___/|___/");
    }

    public void company(){
        System.out.println("________________________________________________________________________________________");
        System.out.println("   ___  ___   __  __  ___   _    _  _  ___  ___  ___   _________________________________");
        System.out.println("  / __|/ _ \\ |  \\/  || _ \\ /_\\  | \\| ||_ _|| __|/ __|");
        System.out.println(" | (__| (_) || |\\/| ||  _// _ \\ | .` | | | | _| \\__ \\           ******* Sociétés *******");
        System.out.println("  \\___|\\___/ |_|  |_||_| /_/ \\_\\|_|\\_||___||___||___/");
    }
}
