package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.ContractController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ContractMenu {

    private final ContractController contractController;
    private final CompanyController companyController;
    private final JobController jobController;
    private final EmployeeController employeeController;
    private final IHMUtilsGet ihmUtilsGet;
    private final IHMUtilsSet ihmUtilsSet;
    private final Stylized3LText stylized3LText;
    private final Stylized4LText stylized4LText;

    public void contractMenu(Scanner scanner) {
        displayContractMenu();
        String chosenContractMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenContractMenuItem);
        contractMainSwitch(choice, scanner);
    }

    private void contractMainSwitch(Integer choice, Scanner scanner) {
        switch (choice) {
            case 1:
                stylized3LText.listContracts();
                listContracts(scanner);
                break;
            case 2:
                stylized3LText.createContract();
                createContract(scanner);
                break;
            case 3:
                stylized3LText.updateContract();
                updateContract(scanner);
                break;
            case 4:
                stylized3LText.deleteContract();
                deleteContract(scanner);
                break;
        }
    }

    private List<ContractDto> listContracts(Scanner scanner){
        List<ContractDto> contracts = null;
        String prompt = "";
        Class<?> awaitedType = selectContractByCompanyOrEmployee(scanner, "Lister les contrats par :");
        if(awaitedType.equals(Company.class)){
                       CompanyDto selectedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
            prompt = "l'entreprise " + selectedCompany.name();
            contracts = contractController.getAllContractsByCompanyId(selectedCompany.id());
        } else if(awaitedType.equals(Employee.class)){
            EmployeeDto selectedEmployee = ihmUtilsGet.getSelectedEmployee(scanner, employeeController.getAll());
            prompt = "du salarié " + selectedEmployee.firstname() + " " + selectedEmployee.lastname();
            contracts = contractController.getAllContractsByEmployeeId(selectedEmployee.id());
        }
        if (contracts == null || contracts.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("No contracts to display"));
        } else {
            System.out.println("");
            System.out.println("Liste des contrats " + prompt + " :");
            for (ContractDto contract : contracts) {
                System.out.println("- Id: " + contract.id() + ", title: " + contract.title());
            }
            return contracts;
        }
    }

    private void createContract(Scanner scanner){
        CompanyDto selectedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        EmployeeDto selectedEmployee = ihmUtilsGet.getSelectedEmployee(scanner,employeeController.getAll());
        if(selectedCompany != null
                && selectedCompany.id() != 0
                && selectedEmployee != null
                && selectedEmployee.id() != 0){
            ContractDto contractDto = ihmUtilsSet.createContractDto(scanner, selectedCompany, jobController.getAllJobsWithoutContractByCompanyId(selectedCompany.id()), selectedEmployee.id());
            contractController.create(contractDto);
        } else {
            System.out.println("L'entreprise et le salarié doivent être valide !");
            if (selectedEmployee == null || selectedEmployee.id() == 0) {
                System.out.println("L'employé n'est pas valide.");
            }
            if (selectedCompany == null || selectedCompany.id() == 0){
                System.out.println("L'entreprise n'est pas valide");
            }
        }
    }

    private void updateContract(Scanner scanner){
        List<ContractDto> contracts= listContracts(scanner);
        for (ContractDto contract : contracts) {
            System.out.println("Id: " + contract.id() + ", title: " + contract.title());
        }
        ContractDto selectedContract = ihmUtilsGet.getSelectedContract(scanner, contracts);
        ContractDto updatedContractDto = ihmUtilsSet.updateFieldsContractDto(scanner, selectedContract);
        contractController.update(updatedContractDto);
    }

    private void deleteContract(Scanner scanner){
        List<ContractDto> contracts= listContracts(scanner);
        for (ContractDto contract : contracts) {
            System.out.println("Id: " + contract.id() + ", title: " + contract.title());
        }
        ContractDto selectedContract = ihmUtilsGet.getSelectedContract(scanner, contracts);
        contractController.deleteById(selectedContract.id());
    }

    private Class<?> selectContractByCompanyOrEmployee(Scanner scanner, String prompt) {
        System.out.println(prompt);
        System.out.println("1 - Entreprise");
        System.out.println("2 - Salarié");
        int choice = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Choix :"));
        return switch (choice) {
            case 1 -> Company.class;
            case 2 -> Employee.class;
            default -> null;
        };
    }

    private void displayContractMenu() {
        stylized4LText.contract();
        System.out.println("");
        System.out.println("");
        System.out.println("1. Liste des contrats");
        System.out.println("2. Créer un contrat");
        System.out.println("3. Modifier un contrat");
        System.out.println("4. Supprimer un contrat");
        System.out.println("");
        System.out.println("Autres touches : retour au menu principal");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    public ContractMenu(ContractController contractController, CompanyController companyController, JobController jobController, EmployeeController employeeController, IHMUtilsGet ihmUtilsGet, IHMUtilsSet ihmUtilsSet, Stylized3LText stylized3LText, Stylized4LText stylized4LText) {
        this.contractController = contractController;
        this.companyController = companyController;
        this.jobController = jobController;
        this.employeeController = employeeController;
        this.ihmUtilsGet = ihmUtilsGet;
        this.ihmUtilsSet = ihmUtilsSet;
        this.stylized3LText = stylized3LText;
        this.stylized4LText = stylized4LText;
    }
}
