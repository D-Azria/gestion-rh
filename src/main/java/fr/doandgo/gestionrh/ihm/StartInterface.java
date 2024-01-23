package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.utils.Stylized3LText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * La classe StartInterface représente l'interface de démarrage de l'application de gestion des ressources humaines.
 */
@Component
public class StartInterface {

    @Autowired
    CompanyMenu companyMenu;

    @Autowired
    JobsMenu jobsMenu;

    @Autowired
    EmployeeMenu employeeMenu;

    @Autowired
    ContractMenu contractMenu;

    @Autowired
    Stylized3LText stylized3LText;

    /**
     * Méthode principale pour démarrer l'interface utilisateur.
     */
    public void startInterface() {
        Scanner scanner = new Scanner(System.in);
        mainDesign();
        int choice = 0;
        do {
            try {
                displayMainMenu();
                String menuItemChosen = scanner.nextLine();
                choice = Integer.parseInt(menuItemChosen);
                System.out.println("");
                switch (choice) {
                    case 0:
                        displayMainMenu();
                    case 1:
                        jobsMenu.jobsMenu(scanner);
                        break;
                    case 2:
                        employeeMenu.employeeMenu(scanner);
                        break;
                    case 3:
                        companyMenu.companyMenu(scanner);
                        break;
                    case 4:
                        contractMenu.contractMenu(scanner);
                        break;
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (choice != 99);
        scanner.close();
        stylized3LText.end();
    }

    /**
     * Affiche le menu principal de l'application.
     */
    public void displayMainMenu() {
        System.out.println("");
        stylized3LText.menu();
        System.out.println("");
        System.out.println("1. Fiches de poste");
        System.out.println("2. Salariés");
        System.out.println("3. Sociétés");
        System.out.println("4. Contracts");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    private void mainDesign() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("________________________________________________________________________________________");
        System.out.println("");
        System.out.println("");
        System.out.println("   _____   ______    _____   _______   _____    ____    _   _           _____    _    _ ");
        System.out.println("  / ____| |  ____|  / ____| |__   __| |_   _|  / __ \\  | \\ | |         |  __ \\  | |  | |");
        System.out.println(" | |  __  | |__    | (___      | |      | |   | |  | | |  \\| |         | |__) | | |__| |");
        System.out.println(" | | |_ | |  __|    \\___ \\     | |      | |   | |  | | | . ` |  [[]]   |  _  /  |  __  |");
        System.out.println(" | |__| | | |____   ____) |    | |     _| |_  | |__| | | |\\  |         | | \\ \\  | |  | |");
        System.out.println("  \\_____| |______| |_____/     |_|    |_____|  \\____/  |_| \\_|         |_|  \\_\\ |_|  |_|");
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\                                                           //////////////");
        System.out.println(" >>>>>>>>>>>>>>----[          Gestion Ressources Humaines          ]----<<<<<<<<<<<<<< ");
        System.out.println("//////////////                                                           \\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }

}
