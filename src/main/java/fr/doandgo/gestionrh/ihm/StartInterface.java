package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.authentication.AuthenticationController;
import fr.doandgo.gestionrh.dto.UserDto;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * La classe StartInterface représente l'interface de démarrage de l'application de gestion des ressources humaines.
 */
@Component
public class StartInterface {

    boolean logged = false;

    private final AuthenticationController authenticationController;
    private final UserMenu userMenu;
    private final CompanyMenu companyMenu;
    private final JobsMenu jobsMenu;
    private final EmployeeMenu employeeMenu;
    private final ContractMenu contractMenu;
    private final Stylized3LText stylized3LText;
    private final IHMUtilsGet ihmUtilsGet;

    /**
     * Méthode principale pour démarrer l'interface utilisateur.
     */
    public void startInterface() {
        Scanner scanner = new Scanner(System.in);
        mainDesign();
        int choice = 0;
        do {
            startMenu();
            String menuItemChosen = scanner.nextLine();
            choice = Integer.parseInt(menuItemChosen);
            switch (choice){
                case 1:
                    loginMenu(scanner);
                    break;
                case 2:
                    userMenu.createUser(scanner);
                    break;
            }

        } while (!logged);

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
                    case 5:
                        userMenu.userMenu(scanner);
                        break;
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (choice != 99);
        scanner.close();
        stylized3LText.end();
    }

    public void startMenu(){
        System.out.println("");
        System.out.println("1. Se connecter");
        System.out.println("2. Créer un compte");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    public void loginMenu(Scanner scanner){
        System.out.println("");
        stylized3LText.login();
        System.out.println("");
        UserDto userToLogin = new UserDto(null, null, null, null,         ihmUtilsGet.getUserInput(scanner,"Email : "), ihmUtilsGet.getUserInput(scanner,"Password : "), null);
        ResponseEntity<?> response = authenticationController.login(userToLogin);

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
        System.out.println("");
        System.out.println("5. Utilisateurs");
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

    public StartInterface(AuthenticationController authenticationController, UserMenu userMenu, CompanyMenu companyMenu, JobsMenu jobsMenu, EmployeeMenu employeeMenu, ContractMenu contractMenu, Stylized3LText stylized3LText, IHMUtilsGet ihmUtilsGet) {
        this.authenticationController = authenticationController;
        this.userMenu = userMenu;
        this.companyMenu = companyMenu;
        this.jobsMenu = jobsMenu;
        this.employeeMenu = employeeMenu;
        this.contractMenu = contractMenu;
        this.stylized3LText = stylized3LText;
        this.ihmUtilsGet = ihmUtilsGet;
    }
}
