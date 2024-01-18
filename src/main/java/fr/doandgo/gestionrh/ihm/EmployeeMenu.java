package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.utils.IHMUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class EmployeeMenu {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private CompanyController companyController;

    @Autowired
    private IHMUtils ihmUtils;

    public void EmployeeMenu(Scanner scanner) {
        displayEmployeeMenu();
        String chosenEmployeeMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenEmployeeMenuItem);
        employeeMainSwitch(choice, scanner);
    }

    private void employeeMainSwitch(Integer choice, Scanner scanner) {
        List<Employee> employees = employeeController.getAll();
        switch (choice) {
            case 1:
                System.out.println("--  Liste des salariés  --");
                for (Employee e : employees) {
                    System.out.println("Id: " + e.getId() + ", firstname: " + e.getFirstname() + ", lastname: " + e.getLastname());
                }
                break;
            case 2:
                Company selectedCompany = ihmUtils.getSelectedCompany(scanner, companyController.getAll());

                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    private void displayEmployeeMenu() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("  ___  __  __  ___  _     ___ __   __ ___  ___  ___   __________________________________");
        System.out.println(" | __||  \\/  || _ \\| |   / _ \\\\ \\ / /|_ _|| __|/ __|");
        System.out.println(" | _| | |\\/| ||  _/| |__| (_) |\\ V /  | | | _| \\__ \\            ******* Salariés *******");
        System.out.println(" |___||_|  |_||_|  |____|\\___/  |_|  |___||___||___/");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister tous les salariés");
        System.out.println("2. Lister les salariés d'une entreprise");
        System.out.println("3. Ajouter un salarié");
        System.out.println("4. Modifier un salarié");
        System.out.println("5. Supprimer un salarié");
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }


}
