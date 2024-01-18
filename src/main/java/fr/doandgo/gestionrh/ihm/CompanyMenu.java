package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CompanyMenu {

    @Autowired
    private CompanyController companyController;

    public void CompanyMenu(Scanner scanner) {
        displayCompanyMenu();
        String chosenCompanyMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenCompanyMenuItem);
        companyMainSwitch(choice, scanner);

    }


    private void displayCompanyMenu() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("   ___  ___   __  __  ___   _    _  _  ___  ___  ___   _________________________________");
        System.out.println("  / __|/ _ \\ |  \\/  || _ \\ /_\\  | \\| ||_ _|| __|/ __|");
        System.out.println(" | (__| (_) || |\\/| ||  _// _ \\ | .` | | | | _| \\__ \\           ******* Sociétés *******");
        System.out.println("  \\___|\\___/ |_|  |_||_| /_/ \\_\\|_|\\_||___||___||___/");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les entreprises");
        System.out.println("2. Créer une entreprise");
        System.out.println("3. Supprimer une entreprise");
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    private void companyMainSwitch(Integer choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("--  Liste des entreprises  --");
                for (Company company : companyController.getAll()) {
                    System.out.println("Id: " + company.getId() + ", name: " + company.getName());
                }
                break;
            case 2:
                System.out.println("Créer une entreprise :");
                System.out.print("Nom :");
                String companyName = scanner.nextLine();
                System.out.print("URL :");
                String companyURL = scanner.nextLine();
                CompanyDto companyDto = new CompanyDto(companyName, companyURL);
                companyController.create(companyDto);
                break;
            case 3:
                System.out.println("--  Liste des entreprises  --");
                for (Company company : companyController.getAll()) {
                    System.out.println("Id: " + company.getId() + ", name: " + company.getName());
                }
                System.out.print("Id de l'entreprise à supprimer :");
                String company = scanner.nextLine();
                Integer companyId = Integer.parseInt(company);
                companyController.deleteById(companyId);
                break;
        }
    }

}
