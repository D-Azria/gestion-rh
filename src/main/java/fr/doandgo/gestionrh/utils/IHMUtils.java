package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.Service;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class IHMUtils {

    public boolean controlParsedTypeOfUserInput(String input, Class<?> awaitedType){
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
        for (int i = 1; i <= Service.values().length; i++) {
            System.out.println((i) + " - " + Service.values()[i-1]);
        }
        String serviceChoice = getUserInput(scanner, "Choix du numéro de service:");
        try {
            int serviceId = Integer.parseInt(serviceChoice);
            return Service.values()[serviceId];
        } catch (Exception e) {
            return null;
        }
    }

    public Category getSelectedCategory(Scanner scanner) {
        for (int i = 1; i <= Category.values().length; i++) {
            System.out.println((i) + " - " + Category.values()[i-1]);
        }
        String categoryChoice = getUserInput(scanner, "Choix du numéro de categorie:");
        try {
            int categoryId = Integer.parseInt(categoryChoice);
            return Category.values()[categoryId];
        } catch (Exception e) {
            return null;
        }

    }

    public Company getSelectedCompany(Scanner scanner, List<Company> companies) {

        for (Company company : companies) {
            System.out.println("Id: " + company.getId() + ", name: " + company.getName());
        }
        String companyChoice = getUserInput(scanner, "Choix de l'entreprise : ");
        try {
            int companyId = Integer.parseInt(companyChoice);
            Optional<Company> selectedCompany = companies.stream().filter(comp -> Objects.equals(comp.getId(), companyId)).findFirst();
            if (selectedCompany.isEmpty() || selectedCompany.get() == null) {
                throw new NotFoundOrValidException(new MessageDto("Company not found"));
            } else {
                return selectedCompany.get();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }

    public Date getFormattedDate(Scanner scanner, String prompt){
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

    public Job getSelectedJobOfCompany(Scanner scanner, List<Job> jobs, Company company){
        System.out.println( jobs.size() +" postes disponibles dans l'entreprise " + company.getName() + " :");
        for (Job j : jobs){
            System.out.println(j.getId() + " - " + j.getName() + ", "+ j.getCategory() + ", " + j.getService());
        }
        String jobChoice = getUserInput(scanner, "Choix du poste : ");
        try {
            int jobId = Integer.parseInt(jobChoice);
            Optional<Job> selectedJob = jobs.stream().filter(comp -> Objects.equals(comp.getId(), jobId)).findFirst();
            if (selectedJob.isEmpty() || selectedJob.get() == null) {
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
        for (int i = 1; i <= ContractTypes.values().length; i++) {
            System.out.println((i) + " - " + ContractTypes.values()[i-1]);
        }
        String contractTypeChoice = getUserInput(scanner, "Choix du numéro de type de contrat:");
        try {
            int contractTypeId = Integer.parseInt(contractTypeChoice);
            return ContractTypes.values()[contractTypeId];
        } catch (Exception e) {
            return null;
        }
    }

    public WorkingConditions getWorkingConditions(Scanner scanner) {
        for (int i = 1; i <= WorkingConditions.values().length; i++) {
            System.out.println((i) + " - " + WorkingConditions.values()[i-1]);
        }
        String workingConditionsChoice = getUserInput(scanner, "Choix du numéro de type de contrat:");
        try {
            int workingConditionsId = Integer.parseInt(workingConditionsChoice);
            return WorkingConditions.values()[workingConditionsId];
        } catch (Exception e) {
            return null;
        }
    }
}
