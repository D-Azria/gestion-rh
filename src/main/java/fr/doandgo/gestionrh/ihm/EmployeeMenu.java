package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.IHMUtilsSet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EmployeeMenu {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private CompanyController companyController;
    @Autowired
    private JobController jobController;
    @Autowired
    private IHMUtilsGet ihmUtilsGet;
    @Autowired
    private IHMUtilsSet ihmUtilsSet;
    @Autowired
    private Stylized3LText stylized3LText;
    @Autowired
    private Stylized4LText stylized4LText;


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

    private List<Employee> listEmployees(){
        List<Employee> employees = employeeController.getAll();
        System.out.println("--  Liste des salariés  --");
        for (Employee e : employees) {
            System.out.println("Id: " + e.getId() + ", firstname: " + e.getFirstname() + ", lastname: " + e.getLastname());
        }
        return employees;
    }

    private List<Employee> listEmployeesOfCompany(Scanner scanner){
        Company selectedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        List<Employee> companyEmployees = employeeController.getAllEmployeesByCompanyId(selectedCompany.getId());
        for (Employee e : companyEmployees) {
            System.out.println("Id: " + e.getId() + ", firstname: " + e.getFirstname() + ", lastname: " + e.getLastname());
        }
        return companyEmployees;
    }

    private void createEmployee(Scanner scanner){
        Company company = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        List<Job> jobsOfCompany = jobController.getAllJobsWithoutContractByCompanyId(company.getId());
        ContractDto contractDto = ihmUtilsSet.createContractDto(scanner,company, jobsOfCompany, null);
        EmployeeDto employeeDto = ihmUtilsSet.createEmployeeDto(scanner, contractDto);
        employeeController.create(employeeDto);
    }

    private void updateEmployee(Scanner scanner){
        List<Employee> employees = listEmployees();
        System.out.println("--  Sélection du salarié  --");
        for (Employee e : employees) {
            System.out.println("Id: " + e.getId() + ", firstname: " + e.getFirstname() + ", lastname: " + e.getLastname());
        }
        Integer employeeId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Modifier le salarié n° : "));
        Optional<Employee> optionalEmployee =  employees.stream().filter(emp -> Objects.equals(emp.getId(), employeeId)).findFirst();
        if(optionalEmployee.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Employee not found"));
        }else {
            EmployeeDto updateEmployeeDto = ihmUtilsSet.updateEmployeeDto(scanner, optionalEmployee.get());
            employeeController.update(updateEmployeeDto);
        }
    }

    public void deleteEmployee(Scanner scanner){
        List<Employee> employees = listEmployees();
        System.out.println("--  Sélection du salarié  --");
        for (Employee e : employees) {
            System.out.println("Id: " + e.getId() + ", firstname: " + e.getFirstname() + ", lastname: " + e.getLastname());
        }
        Integer employeeId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Supprimer le salarié n° : "));
        Optional<Employee> optionalEmployee =  employees.stream().filter(emp -> Objects.equals(emp.getId(), employeeId)).findFirst();
        if(optionalEmployee.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Employee not found"));
        }else {
            employeeController.deleteById(optionalEmployee.get().getId());
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
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

}
