package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.ContractController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ContractMenu {

    @Autowired
    private ContractController contractController;
    @Autowired
    private CompanyController companyController;
    @Autowired
    private JobController jobController;
    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private IHMUtilsGet ihmUtilsGet;

    @Autowired
    private IHMUtilsSet ihmUtilsSet;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private Stylized3LText stylized3LText;
    @Autowired
    private Stylized4LText stylized4LText;

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

    private List<Contract> listContracts(Scanner scanner){
        List<Contract> contracts = null;
        String prompt = "";
        Class<?> awaitedType = selectContractByCompanyOrEmployee(scanner, "Lister les contrats par :");
        if(awaitedType.equals(Company.class)){
                       Company selectedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
            prompt = "l'entreprise " + selectedCompany.getName();
            contracts = contractController.getAllContractsByCompanyId(selectedCompany.getId());
        } else if(awaitedType.equals(Employee.class)){
            Employee selectedEmployee = ihmUtilsGet.getSelectedEmployee(scanner, employeeController.getAll());
            prompt = "du salarié " + selectedEmployee.getFirstname() + " " + selectedEmployee.getLastname();
            contracts = contractController.getAllContractsByEmployeeId(selectedEmployee.getId());
        }
        if (contracts == null || contracts.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("No contracts to display"));
        } else {
            System.out.println("");
            System.out.println("Liste des contrats " + prompt + " :");
            for (Contract contract : contracts) {
                System.out.println("- Id: " + contract.getId() + ", title: " + contract.getTitle());
            }
            return contracts;
        }
}

    private void createContract(Scanner scanner){
        System.out.println("Créer un contrat");
        Company selectedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        Employee selectedEmployee = ihmUtilsGet.getSelectedEmployee(scanner,employeeController.getAll());
        ContractDto contractDto = ihmUtilsSet.createContractDto(scanner, selectedCompany, jobController.getAllJobsWithoutContractByCompanyId(selectedCompany.getId()), selectedEmployee);
        contractController.create(contractDto);
    }

    private void updateContract(Scanner scanner){
        List<Contract> contracts= listContracts(scanner);
        for (Contract contract : contracts) {
            System.out.println("Id: " + contract.getId() + ", title: " + contract.getTitle());
        }
        Contract selectedContract = ihmUtilsGet.getSelectedContract(scanner, contracts);
        ContractDto updatedContractDto = ihmUtilsSet.updateFieldsContractDto(scanner, selectedContract);
        contractController.update(updatedContractDto);
    }

    private void deleteContract(Scanner scanner){
        List<Contract> contracts= listContracts(scanner);
        for (Contract contract : contracts) {
            System.out.println("Id: " + contract.getId() + ", title: " + contract.getTitle());
        }
        Contract selectedContract = ihmUtilsGet.getSelectedContract(scanner, contracts);
        contractController.deleteById(selectedContract.getId());
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
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }
}
