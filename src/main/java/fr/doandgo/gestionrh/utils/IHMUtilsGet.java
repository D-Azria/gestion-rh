package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.*;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class IHMUtilsGet {

    //TODO : implement class check on inputs
    public boolean controlParsedTypeOfUserInput(String input, Class<?> awaitedType) {
        try {
            awaitedType.getMethod("valueOf", String.class).invoke(null, input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public Service getSelectedService(Scanner scanner) {
        System.out.println("Sélection du service : ");
        for (int i = 1; i <= Service.values().length; i++) {
            System.out.println((i) + " - " + Service.values()[i - 1]);
        }
        String serviceChoice = getUserInput(scanner, "Service n° : ");
        try {
            int serviceId = Integer.parseInt(serviceChoice);
            return Service.values()[serviceId];
        } catch (Exception e) {
            return null;
        }
    }

    public Category getSelectedCategory(Scanner scanner) {
        System.out.println("Sélection de la categorie : ");
        for (int i = 1; i <= Category.values().length; i++) {
            System.out.println((i) + " - " + Category.values()[i - 1]);
        }
        String categoryChoice = getUserInput(scanner, "Sélection de la categorie : ");
        try {
            int categoryId = Integer.parseInt(categoryChoice);
            return Category.values()[categoryId];
        } catch (Exception e) {
            return null;
        }
    }

    public Company getSelectedCompany(Scanner scanner, List<Company> companies) {
        System.out.println("Sélection de l'entreprise : ");
        for (Company company : companies) {
            System.out.println("Id: " + company.getId() + ", name: " + company.getName());
        }
        String companyChoice = getUserInput(scanner, "Entreprise n° : ");
        try {
            int companyId = Integer.parseInt(companyChoice);
            Optional<Company> selectedCompany = companies.stream().filter(comp -> Objects.equals(comp.getId(), companyId)).findFirst();
            if (selectedCompany.isEmpty()) {
                throw new NotFoundOrValidException(new MessageDto("Company not found"));
            } else {
                return selectedCompany.get();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }

    public Date getFormattedDate(Scanner scanner, String prompt) {
        Date dateValidated = new Date();
        boolean validDate = false;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        do {
            String birthDate = getUserInput(scanner, prompt);
            try {
                dateValidated = dateFormatter.parse(birthDate);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy.");
            }
        } while (!validDate);
        return dateValidated;
    }

    public Job getSelectedJobOfCompany(Scanner scanner, List<Job> jobs, Company company) {
        System.out.println(jobs.size() + " offres disponibles dans l'entreprise " + company.getName() + ". Sélection du poste :");
        for (Job j : jobs) {
            System.out.println(j.getId() + " - " + j.getName() + ", " + j.getCategory() + ", " + j.getService());
        }
        String jobChoice = getUserInput(scanner, "=> Poste n° : ");
        try {
            int jobId = Integer.parseInt(jobChoice);
            Optional<Job> selectedJob = jobs.stream().filter(comp -> Objects.equals(comp.getId(), jobId)).findFirst();
            if (selectedJob.isEmpty()) {
                throw new NotFoundOrValidException(new MessageDto("Job not found"));
            } else {
                return selectedJob.get();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }

    public ContractTypes getContractType(Scanner scanner) {
        System.out.println("Sélection du type de contrat : ");
        for (int i = 1; i <= ContractTypes.values().length; i++) {
            System.out.println((i) + " - " + ContractTypes.values()[i - 1]);
        }
        String contractTypeChoice = getUserInput(scanner, "=> Type de contrat n° : ");
        try {
            int contractTypeId = Integer.parseInt(contractTypeChoice);
            return ContractTypes.values()[contractTypeId];
        } catch (Exception e) {
            return null;
        }
    }

    public WorkingConditions getWorkingConditions(Scanner scanner) {
        System.out.println("Sélection  des conditions de travail :");
        for (int i = 1; i <= WorkingConditions.values().length; i++) {
            System.out.println((i) + " - " + WorkingConditions.values()[i - 1]);
        }
        String workingConditionsChoice = getUserInput(scanner, "=> Conditions de travail n° :");
        try {
            int workingConditionsId = Integer.parseInt(workingConditionsChoice);
            return WorkingConditions.values()[workingConditionsId];
        } catch (Exception e) {
            return null;
        }
    }

    public TerminationReason getTerminationReason(Scanner scanner) {
        System.out.println("Sélection de la raison de la fin du contrat : ");
        for (int i = 1; i <= TerminationReason.values().length; i++) {
            System.out.println((i) + " - " + TerminationReason.values()[i - 1]);
        }
        String terminationReasonChoice = getUserInput(scanner, "=> Raison de la fin du contrat n° : ");
        try {
            int terminationReasonId = Integer.parseInt(terminationReasonChoice);
            return TerminationReason.values()[terminationReasonId];
        } catch (Exception e) {
            return null;
        }
    }

    public Employee getSelectedEmployee(Scanner scanner, List<Employee> employees) {
        System.out.println("Sélection du salarié : ");
        for (Employee employee : employees) {
            System.out.println("Id: " + employee.getId() + ", name: " + employee.getFirstname() + " " + employee.getLastname());
        }
        String employeeChoice = getUserInput(scanner, "=> Salarié n° : ");
        try {
            int employeeId = Integer.parseInt(employeeChoice);
            Optional<Employee> selectedEmployee = employees.stream().filter(emp -> Objects.equals(emp.getId(), employeeId)).findFirst();
            if (selectedEmployee.isEmpty()) {
                throw new NotFoundOrValidException(new MessageDto("Company not found"));
            } else {
                return selectedEmployee.get();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }

    public Contract getSelectedContract(Scanner scanner, List<Contract> contracts) {
        System.out.println("Sélection du contrat : ");
        for (Contract contract : contracts) {
            System.out.println("Id: " + contract.getId() + ", name: " + contract.getTitle() + ", company: " + contract.getJob().getCompany().getName());
        }
        String contractChoice = getUserInput(scanner, "=> Contrat n° : ");
        try {
            int contractId = Integer.parseInt(contractChoice);
            Optional<Contract> selectedContract = contracts.stream().filter(ctrct -> Objects.equals(ctrct.getId(), contractId)).findFirst();
            if (selectedContract.isEmpty()) {
                throw new NotFoundOrValidException(new MessageDto("Company not found"));
            } else {
                return selectedContract.get();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
}
