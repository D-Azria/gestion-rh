package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.*;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.IHMUtilsSet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeMenu {
    private final EmployeeController employeeController;
    private final CompanyController companyController;
    private final JobController jobController;
    private final IHMUtilsGet ihmUtilsGet;
    private final IHMUtilsSet ihmUtilsSet;
    private final Stylized3LText stylized3LText;
    private final Stylized4LText stylized4LText;

    public void employeeMenu(Scanner scanner) {
        displayEmployeeMenu();
        String chosenEmployeeMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenEmployeeMenuItem);
        employeeMainSwitch(choice, scanner);
    }

    private void employeeMainSwitch(Integer choice, Scanner scanner) {
        switch (choice) {
            case 1:
                stylized3LText.listEmployees();
                listEmployees();
                break;
            case 2:
                stylized3LText.listEmployees();
                listEmployeesOfCompany(scanner);
                break;
            case 3:
                stylized3LText.createEmployee();
                createEmployee(scanner);
                break;
            case 4:
                stylized3LText.updateEmployee();
                updateEmployee(scanner);
                break;
            case 5:
                stylized3LText.deleteEmployee();
                deleteEmployee(scanner);
                break;
        }
    }

    private List<EmployeeDto> listEmployees(){
        List<EmployeeDto> employees = employeeController.getAll();
        for (EmployeeDto e : employees) {
            System.out.println("Id: " + e.id() + ", firstname: " + e.firstname() + ", lastname: " + e.lastname());
        }
        return employees;
    }

    private List<EmployeeDto> listEmployeesOfCompany(Scanner scanner){
        CompanyDto selectedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        List<EmployeeDto> companyEmployees = employeeController.getAllEmployeesByCompanyId(selectedCompany.id());
        for (EmployeeDto e : companyEmployees) {
            System.out.println("Id: " + e.id() + ", firstname: " + e.firstname() + ", lastname: " + e.lastname());
        }
        return companyEmployees;
    }

    private void createEmployee(Scanner scanner){
        CompanyDto company = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        List<JobDto> jobsOfCompany = jobController.getAllJobsWithoutContractByCompanyId(company.id());
        ContractDto contractDto = ihmUtilsSet.createContractDto(scanner,company, jobsOfCompany, null);
        EmployeeDto employeeDto = ihmUtilsSet.createEmployeeDto(scanner, contractDto);
        employeeController.create(employeeDto);
    }

    private void updateEmployee(Scanner scanner){
        List<EmployeeDto> employees = listEmployees();
        System.out.println("--  Sélection du salarié  --");
        for (EmployeeDto e : employees) {
            System.out.println("Id: " + e.id() + ", firstname: " + e.firstname() + ", lastname: " + e.lastname());
        }
        Integer employeeId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Modifier le salarié n° : "));
        Optional<EmployeeDto> optionalEmployee =  employees.stream().filter(emp -> Objects.equals(emp.id(), employeeId)).findFirst();
        if(optionalEmployee.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Employee not found"));
        }else {
            EmployeeDto updateEmployeeDto = ihmUtilsSet.updateEmployeeDto(scanner, optionalEmployee.get());
            employeeController.update(updateEmployeeDto);
        }
    }

    public void deleteEmployee(Scanner scanner){
        List<EmployeeDto> employees = listEmployees();
        System.out.println("--  Sélection du salarié  --");
        for (EmployeeDto e : employees) {
            System.out.println("Id: " + e.id() + ", firstname: " + e.firstname() + ", lastname: " + e.lastname());
        }
        Integer employeeId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Supprimer le salarié n° : "));
        Optional<EmployeeDto> optionalEmployee =  employees.stream().filter(emp -> Objects.equals(emp.id(), employeeId)).findFirst();
        if(optionalEmployee.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Employee not found"));
        }else {
            employeeController.deleteById(optionalEmployee.get().id());
        }
    }

    private void displayEmployeeMenu() {
        stylized4LText.employee();
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister tous les salariés");
        System.out.println("2. Lister les salariés d'une entreprise");
        System.out.println("3. Ajouter un salarié");
        System.out.println("4. Modifier un salarié");
        System.out.println("5. Supprimer un salarié");
        System.out.println("");
        System.out.println("Autres touches : retour au menu principal");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    public EmployeeMenu(EmployeeController employeeController, CompanyController companyController, JobController jobController, IHMUtilsGet ihmUtilsGet, IHMUtilsSet ihmUtilsSet, Stylized3LText stylized3LText, Stylized4LText stylized4LText) {
        this.employeeController = employeeController;
        this.companyController = companyController;
        this.jobController = jobController;
        this.ihmUtilsGet = ihmUtilsGet;
        this.ihmUtilsSet = ihmUtilsSet;
        this.stylized3LText = stylized3LText;
        this.stylized4LText = stylized4LText;
    }
}
