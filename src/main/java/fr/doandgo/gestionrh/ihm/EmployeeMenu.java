package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import fr.doandgo.gestionrh.utils.DateUtils;
import fr.doandgo.gestionrh.utils.IHMUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class EmployeeMenu {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private CompanyController companyController;
    @Autowired
    private JobController jobController;

    @Autowired
    private IHMUtils ihmUtils;

    @Autowired
    private DateUtils dateUtils;

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
                List<Employee> companyEmployees = employeeController.getAllEmployeesByCompanyId(selectedCompany.getId());
                for (Employee e : companyEmployees) {
                    System.out.println("Id: " + e.getId() + ", firstname: " + e.getFirstname() + ", lastname: " + e.getLastname());
                }
                break;
            case 3:
                // Add an employee AND create his contract
                EmployeeDto employeeDto = createEmployeeDto(scanner);
                employeeController.create(employeeDto);
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

    private EmployeeDto createEmployeeDto(Scanner scanner){
        Company selectedCompany = ihmUtils.getSelectedCompany(scanner, companyController.getAll());

        String firstname = ihmUtils.getUserInput(scanner, "Prénom : ");
        String lastname = ihmUtils.getUserInput(scanner, "NOM : ");
        Date birthDate = ihmUtils.getFormattedDate(scanner, "Date de naissance (format: dd/MM/yyyy) : ");

        return new EmployeeDto(firstname , lastname, birthDate, new ArrayList<>(), new ArrayList<>(), createContractDto(scanner, selectedCompany));
    }

    private ContractDto createContractDto(Scanner scanner, Company selectedCompany){
        List<Job> jobsOfCompany = jobController.getAllJobsWithoutContractByCompanyId(selectedCompany.getId());
        System.out.println("Créer le contrat : ");
        String title = ihmUtils.getUserInput(scanner, "Titre :");

        boolean validDates = false;
        Date  signatureDate;
        Date startDate;
        Date   plannedEndDate;
        do {
             signatureDate = ihmUtils.getFormattedDate(scanner, "Date de signature (format: dd/MM/yyyy) : ");
             startDate = ihmUtils.getFormattedDate(scanner, "Date de début (format: dd/MM/yyyy) : ");
               plannedEndDate = ihmUtils.getFormattedDate(scanner, "Date de fin prévue (format: dd/MM/yyyy) : ");
            if(dateUtils.dateIsPresentOrFuture(signatureDate) && dateUtils.dateIsPresentOrFuture(startDate) && dateUtils.dateIsPresentOrFuture(plannedEndDate) && startDate.before(plannedEndDate) && dateUtils.isFirstDateBeforeOrSameSecondDate(signatureDate, startDate) && dateUtils.isFirstDateBeforeOrSameSecondDate(startDate, plannedEndDate)){
                validDates = true;
            } else {
                System.out.println("Dates are not valid. Signature date must be same or before start date, planned end date must be after them.");
            }
        } while(!validDates);

        Double salary = Double.parseDouble(ihmUtils.getUserInput(scanner,"Salaire : "));
        ContractTypes type= ihmUtils.getContractType(scanner);
        WorkingConditions workingConditions= ihmUtils.getWorkingConditions(scanner);

        Job job = ihmUtils.getSelectedJobOfCompany(scanner, jobsOfCompany, selectedCompany);
        ContractDto contratDto = new ContractDto(title, signatureDate, startDate, null, plannedEndDate, salary, type, null, workingConditions, null, job, new ArrayList<>());
        return contratDto;
    }


}
