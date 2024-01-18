package fr.doandgo.gestionrh.ihm;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class JobsMenu {
    public void JobsMenu(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            try {
                displayJobsMenu();
                String menuItemChosen = scanner.nextLine();
                choice = Integer.parseInt(menuItemChosen);
                System.out.println("");
                switch (choice) {
                    case 1:
                        System.out.println("***** Fiches de poste *****");
                        break;
                    case 2:
                        System.out.println("***** Salariés *****");
                        break;
                }
            }
            catch(Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (choice != 0) ;
        scanner.close();
    }

    private static void displayJobsMenu() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("     _   ___   ___  ___    _____________________________________________________________");
        System.out.println("  _ | | / _ \\ | _ )/ __|");
        System.out.println(" | || || (_) || _ \\__ \\                                  ******* Fiches de poste *******");
        System.out.println("  \\__/  \\___/ |___/|___/");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les offres");
        System.out.println("2. Créer une fiche de poste");
        System.out.println("0. Retour au menu principal");
        System.out.println("99. Quitter");
    }
}
