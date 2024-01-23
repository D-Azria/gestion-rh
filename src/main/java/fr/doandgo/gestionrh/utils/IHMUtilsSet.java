package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.TerminationReason;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class IHMUtilsSet {

    @Autowired
    IHMUtilsGet ihmUtilsGet;

    @Autowired
    private DateUtils dateUtils;

    public EmployeeDto createEmployeeDto(Scanner scanner, ContractDto contractDto) {

        String firstname = ihmUtilsGet.getUserInput(scanner, "Prénom : ");
        String lastname = ihmUtilsGet.getUserInput(scanner, "NOM : ");
        Date birthDate = ihmUtilsGet.getFormattedDate(scanner, "Date de naissance (format: dd/MM/yyyy) : ");

        return new EmployeeDto(null, firstname, lastname, birthDate, new ArrayList<>(), new ArrayList<>(), contractDto);
    }

    public EmployeeDto updateEmployeeDto(Scanner scanner, Employee employee) {

        String firstname = ihmUtilsGet.getUserInput(scanner, "Prénom : ");
        String lastname = ihmUtilsGet.getUserInput(scanner, "NOM : ");
        Date birthDate = ihmUtilsGet.getFormattedDate(scanner, "Date de naissance (format: dd/MM/yyyy) : ");

        return new EmployeeDto(employee.getId(), firstname, lastname, birthDate, employee.getDiplomas(), employee.getContracts(), null);
    }

    public ContractDto createContractDto(Scanner scanner, Company company, List<Job> jobsOfCompany, Employee employee) {
        System.out.println("Créer le contrat : ");
        String title = ihmUtilsGet.getUserInput(scanner, "Titre :");

        boolean validDates = false;
        Date signatureDate;
        Date startDate;
        Date plannedEndDate;
        do {
            signatureDate = ihmUtilsGet.getFormattedDate(scanner, "Date de signature (format: dd/MM/yyyy) : ");
            startDate = ihmUtilsGet.getFormattedDate(scanner, "Date de début (format: dd/MM/yyyy) : ");
            plannedEndDate = ihmUtilsGet.getFormattedDate(scanner, "Date de fin prévue (format: dd/MM/yyyy) : ");
            if (dateUtils.dateIsPresentOrFuture(signatureDate)
                    && dateUtils.dateIsPresentOrFuture(startDate)
                    && dateUtils.dateIsPresentOrFuture(plannedEndDate)
                    && startDate.before(plannedEndDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(signatureDate, startDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(startDate, plannedEndDate)) {
                validDates = true;
            } else {
                System.out.println("Dates are not valid. Signature date must be same or before start date, planned end date must be after them.");
            }
        } while (!validDates);

        Double salary = Double.parseDouble(ihmUtilsGet.getUserInput(scanner, "Salaire : "));
        ContractTypes type = ihmUtilsGet.getContractType(scanner);
        WorkingConditions workingConditions = ihmUtilsGet.getWorkingConditions(scanner);

        Job job = ihmUtilsGet.getSelectedJobOfCompany(scanner, jobsOfCompany, company);

        Integer employeeId = employee != null ? employee.getId() : null;


        return new ContractDto(null, title, signatureDate, startDate, null, plannedEndDate, salary, type, null, workingConditions, employeeId, null, job, new ArrayList<>());
    }

    public ContractDto updateFieldsContractDto(Scanner scanner, Contract contract) {
        System.out.println("Modifier le contrat : ");
        String title = ihmUtilsGet.getUserInput(scanner, "Titre :");

        boolean validDates = false;
        Date endDate;

        do {
            System.out.println("Signature date : " + contract.getSignatureDate());
            System.out.println("Start date : " + contract.getStartDate());
            System.out.println("Planned end date : " + contract.getPlannedEndDate());
            endDate = ihmUtilsGet.getFormattedDate(scanner, "Date de fin du contrat (format: dd/MM/yyyy) : ");

            if (contract.getStartDate().before(endDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(contract.getSignatureDate(), endDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(contract.getStartDate(), endDate)) {
                validDates = true;
            } else {
                System.out.println("Dates are not valid. End date must be after signature and start date.");
            }
        } while (!validDates);

        WorkingConditions workingConditions = ihmUtilsGet.getWorkingConditions(scanner);
        TerminationReason terminationReason = ihmUtilsGet.getTerminationReason(scanner);

        return new ContractDto(contract.getId(), title, contract.getSignatureDate(), contract.getStartDate(), endDate, contract.getPlannedEndDate(), contract.getSalary(), contract.getType(), terminationReason, workingConditions, contract.getEmployee().getId(), contract.getEmployee(), contract.getJob(), contract.getAmendments());
    }

    public ContractDto rewriteContractDto(Scanner scanner, Contract contract) {
        System.out.println("Réécrire le contrat : ");
        String title = ihmUtilsGet.getUserInput(scanner, "Titre :");

        boolean validDates = false;
        Date signatureDate;
        Date startDate;
        Date plannedEndDate;
        do {
            signatureDate = ihmUtilsGet.getFormattedDate(scanner, "Date de signature (format: dd/MM/yyyy) : ");
            startDate = ihmUtilsGet.getFormattedDate(scanner, "Date de début (format: dd/MM/yyyy) : ");
            plannedEndDate = ihmUtilsGet.getFormattedDate(scanner, "Date de fin prévue (format: dd/MM/yyyy) : ");
            if (dateUtils.dateIsPresentOrFuture(signatureDate)
                    && dateUtils.dateIsPresentOrFuture(startDate)
                    && dateUtils.dateIsPresentOrFuture(plannedEndDate)
                    && startDate.before(plannedEndDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(signatureDate, startDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(startDate, plannedEndDate)) {
                validDates = true;
            } else {
                System.out.println("Dates are not valid. Signature date must be same or before start date, planned end date must be after them.");
            }
        } while (!validDates);

        Double salary = Double.parseDouble(ihmUtilsGet.getUserInput(scanner, "Salaire : "));
        ContractTypes type = ihmUtilsGet.getContractType(scanner);
        WorkingConditions workingConditions = ihmUtilsGet.getWorkingConditions(scanner);

        return new ContractDto(contract.getId(), title, signatureDate, startDate, null, plannedEndDate, salary, type, null, workingConditions, contract.getEmployee().getId(), contract.getEmployee(),contract.getJob(), new ArrayList<>());
    }
}
