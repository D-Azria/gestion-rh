package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.dto.*;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.*;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.service.JobService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@AllArgsConstructor
@Data
public class IHMUtilsGet {

    private final JobService jobService;

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
        for (int i = 0; i < Service.values().length; i++) {
            System.out.println((i+1) + " - " + Service.values()[i]);
        }
        String serviceChoice = getUserInput(scanner, "Service n° : ");
        try {
            int serviceId = Integer.parseInt(serviceChoice);
            return Service.values()[serviceId-1];
        } catch (Exception e) {
            return null;
        }
    }

    public Category getSelectedCategory(Scanner scanner) {
        System.out.println("Sélection de la categorie : ");
        for (int i = 0; i < Category.values().length; i++) {
            System.out.println((i+1) + " - " + Category.values()[i]);
        }
        String categoryChoice = getUserInput(scanner, "Sélection de la categorie : ");
        try {
            int categoryId = Integer.parseInt(categoryChoice);
            return Category.values()[categoryId-1];
        } catch (Exception e) {
            return null;
        }
    }

    public CompanyDto getSelectedCompany(Scanner scanner, List<CompanyDto> companies) {
        System.out.println("Sélection de l'entreprise : ");
        for (CompanyDto company : companies) {
            System.out.println("Id: " + company.id() + " - " + company.name());
        }
        String companyChoice = getUserInput(scanner, "Entreprise n° : ");
        try {
            int companyId = Integer.parseInt(companyChoice);
            Optional<CompanyDto> selectedCompany = companies.stream().filter(comp -> Objects.equals(comp.id(), companyId)).findFirst();
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

    public JobDto getSelectedJobOfCompany(Scanner scanner, List<JobDto> jobs, CompanyDto company) {
        System.out.println("Il y a " + jobs.size() + " offres disponibles dans l'entreprise " + company.name() + ". Sélection du poste :");
        for (JobDto j : jobs) {
            System.out.println(j.id() + " - " + j.name() + ", " + j.category() + ", " + j.service());
        }
        String jobChoice = getUserInput(scanner, "=> Poste n° : ");
        try {
            int jobId = Integer.parseInt(jobChoice);
            Optional<JobDto> selectedJob = jobs.stream().filter(comp -> Objects.equals(comp.id(), jobId)).findFirst();
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
        for (int i = 0; i < ContractTypes.values().length; i++) {
            System.out.println((i+1) + " - " + ContractTypes.values()[i]);
        }
        String contractTypeChoice = getUserInput(scanner, "=> Type de contrat n° : ");
        try {
            int contractTypeId = Integer.parseInt(contractTypeChoice);
            return ContractTypes.values()[contractTypeId-1];
        } catch (Exception e) {
            return null;
        }
    }

    public WorkingConditions getWorkingConditions(Scanner scanner) {
        System.out.println("Sélection  des conditions de travail :");
        for (int i = 0; i < WorkingConditions.values().length; i++) {
            System.out.println((i+1) + " - " + WorkingConditions.values()[i]);
        }
        String workingConditionsChoice = getUserInput(scanner, "=> Conditions de travail n° :");
        try {
            int workingConditionsId = Integer.parseInt(workingConditionsChoice);
            return WorkingConditions.values()[workingConditionsId-1];
        } catch (Exception e) {
            return null;
        }
    }

    public TerminationReason getTerminationReason(Scanner scanner) {
        System.out.println("Sélection de la raison de la fin du contrat : ");
        for (int i = 0; i < TerminationReason.values().length; i++) {
            System.out.println((i+1) + " - " + TerminationReason.values()[i]);
        }
        String terminationReasonChoice = getUserInput(scanner, "=> Raison de la fin du contrat n° : ");
        try {
            int terminationReasonId = Integer.parseInt(terminationReasonChoice);
            return TerminationReason.values()[terminationReasonId-1];
        } catch (Exception e) {
            return null;
        }
    }

    public EmployeeDto getSelectedEmployee(Scanner scanner, List<EmployeeDto> employees) {
        System.out.println("Sélection du salarié : ");
        for (EmployeeDto employee : employees) {
            System.out.println("Id: " + employee.id() + ", nom: " + employee.firstname() + " " + employee.lastname());
        }
        String employeeChoice = getUserInput(scanner, "=> Salarié n° : ");
        try {
            int employeeId = Integer.parseInt(employeeChoice);
            Optional<EmployeeDto> selectedEmployee = employees.stream().filter(emp -> Objects.equals(emp.id(), employeeId)).findFirst();
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

    public ContractDto getSelectedContract(Scanner scanner, List<ContractDto> contracts) {
        System.out.println("Sélection du contrat : ");
        for (ContractDto contract : contracts) {
            System.out.println("Id: " + contract.id() + ", titre: " + contract.title() + ", poste n°: " + contract.jobId());
        }
        String contractChoice = getUserInput(scanner, "=> Contrat n° : ");
        try {
            int contractId = Integer.parseInt(contractChoice);
            Optional<ContractDto> selectedContract = contracts.stream().filter(ctrct -> Objects.equals(ctrct.id(), contractId)).findFirst();
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

    public JobDto getSelectedManager(Scanner scanner, List<Integer> jobIds) {
        List<Job> jobs = jobIds.stream().map(jobService::getJobEntityById).toList();
        System.out.println("Sélection du manager : ");
        for (Job job : jobs) {
            System.out.println("Id: " + job.getId() + ", titre: " + job.getName() + ", service: " + job.getService() + ", categorie:" + job.getCategory());
        }
        System.out.println("Aucun manager : choisir 0.");
        String managerChoice = getUserInput(scanner, "=> Poste n° : ");
        try {
            int jobId = Integer.parseInt(managerChoice);
            if (jobId == 0){return null;}
            Optional<JobDto> selectedManager = jobs.stream().filter(j -> Objects.equals(j.getId(), jobId)).findFirst().map(jobService::jobEntityToDto);
            if (selectedManager.isEmpty()) {
                throw new NotFoundOrValidException(new MessageDto("Job not found"));
            } else {
                return selectedManager.get();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }

    public AppRole getAppRole(Scanner scanner) {
        System.out.println("Sélection  du rôle de l'utilisateur : ");
        for (int i = 0; i < AppRole.values().length; i++) {
            System.out.println((i+1) + " - " + AppRole.values()[i]);
        }
        String appRoleChoice = getUserInput(scanner, "=> Rôle n° :");
        try {
            int appRoleId = Integer.parseInt(appRoleChoice);
            return AppRole.values()[appRoleId-1];
        } catch (Exception e) {
            return null;
        }
    }
}
