package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * La classe StartInterface représente l'interface de démarrage de l'application de gestion des ressources humaines.
 */
@Component
public class StartInterface {


    @Autowired
    private  JobController jobController;

    @Autowired
    CompanyMenu companyMenu;

    @Autowired
    JobsMenu jobsMenu;

    /**
     * Méthode principale pour démarrer l'interface utilisateur.
     */
    public void startInterface() {
        Scanner scanner = new Scanner(System.in);


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
                        jobsMenu.JobsMenu(scanner);
                        displayJobsMenu();
                        String chosenJobMenuItem =  scanner.nextLine();
                        choice = Integer.parseInt(chosenJobMenuItem);
                            switch (choice){
                                case 1:
                                    List<Job> jobs = jobController.getAll();
                                    for(Job job : jobs){
                                        System.out.println("Id: " + job.getId() + ", name: " + job.getName());
                                    }
                                    System.out.println("TOTO 1");
                                    break;
                                case 2:

                                    System.out.println("TOTO 2");
                                    break;
                            }
                        break;
                    case 2:
                        displayEmployeeMenu();
                        break;
                    case 3:
                        companyMenu.CompanyMenu(scanner);
                        /*
                        displayCompanyMenu();
                        String chosenCompanyMenuItem = scanner.nextLine();
                        choice = Integer.parseInt(chosenCompanyMenuItem);
                        companySwitch(choice);

                         */
                        break;
                }
            }
            catch(Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (choice != 99) ;
        scanner.close();
    }

    /**
     * Affiche le menu principal de l'application.
     */
    public  void displayMainMenu(){
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
        System.out.println(" >>>>>>>>>>>>>>----[          Gestion: Ressources Humaines          ]----<<<<<<<<<<<<<< ");
        System.out.println("//////////////                                                           \\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("");
        System.out.println("");
        System.out.println("Menu :");
        System.out.println("1. Fiches de poste");
        System.out.println("2. Salariés");
        System.out.println("3. Sociétés");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    private  void displayJobsMenu() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("     _   ___   ___  ___    _____________________________________________________________");
        System.out.println("  _ | | / _ \\ | _ )/ __|");
        System.out.println(" | || || (_) || _ \\__ \\                                  ******* Fiches de poste *******");
        System.out.println("  \\__/  \\___/ |___/|___/");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les offres");
        System.out.println("2. Créer une fiche de poste");
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    private  void displayEmployeeMenu() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("  ___  __  __  ___  _     ___ __   __ ___  ___  ___   __________________________________");
        System.out.println(" | __||  \\/  || _ \\| |   / _ \\\\ \\ / /|_ _|| __|/ __|");
        System.out.println(" | _| | |\\/| ||  _/| |__| (_) |\\ V /  | | | _| \\__ \\            ******* Salariés *******");
        System.out.println(" |___||_|  |_||_|  |____|\\___/  |_|  |___||___||___/");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les salariés");
        System.out.println("2. Ajouter un salarié");
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    private  void jobSwitch(Integer choice){
        switch (choice){
            case 1:
                List<Job> jobs = jobController.getAll();
                for(Job job : jobs){
                    System.out.println("Id: " + job.getId() + ", name: " + job.getName());
                }
                break;
            case 2:

                break;
        }
    }





}
