package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.configuration.WebSecurityConfiguration;
import fr.doandgo.gestionrh.dto.*;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.enums.AppRole;
import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.TerminationReason;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class IHMUtilsSet {

    private final IHMUtilsGet ihmUtilsGet;
    private final DateUtils dateUtils;

    public EmployeeDto createEmployeeDto(Scanner scanner, ContractDto contractDto) {
        String firstname = ihmUtilsGet.getUserInput(scanner, "Prénom : ");
        String lastname = ihmUtilsGet.getUserInput(scanner, "NOM : ");
        Date birthDate = ihmUtilsGet.getFormattedDate(scanner, "Date de naissance (format: dd/MM/yyyy) : ");

        return new EmployeeDto(null, firstname, lastname, birthDate, new ArrayList<>(), new ArrayList<>(), contractDto);
    }

    public EmployeeDto updateEmployeeDto(Scanner scanner, EmployeeDto employeeDto) {
        String firstname = ihmUtilsGet.getUserInput(scanner, "Prénom : ");
        String lastname = ihmUtilsGet.getUserInput(scanner, "NOM : ");
        Date birthDate = ihmUtilsGet.getFormattedDate(scanner, "Date de naissance (format: dd/MM/yyyy) : ");

        return new EmployeeDto(employeeDto.id(), firstname, lastname, birthDate, employeeDto.diplomas(), employeeDto.contractIds(), null);
    }

    public ContractDto createContractDto(Scanner scanner, CompanyDto company, List<JobDto> jobsOfCompany, Integer employeeId) {
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

        JobDto job = ihmUtilsGet.getSelectedJobOfCompany(scanner, jobsOfCompany, company);

        return new ContractDto(null, title, signatureDate, startDate, null, plannedEndDate, salary, type, null, workingConditions, employeeId, job.id()/*, new ArrayList<>()*/);
    }

    public ContractDto updateFieldsContractDto(Scanner scanner, ContractDto contractDto) {
        System.out.println("Modifier le contrat : ");
        String title = ihmUtilsGet.getUserInput(scanner, "Titre :");

        boolean validDates = false;
        Date endDate;

        do {
            System.out.println("Signature date : " + contractDto.signatureDate());
            System.out.println("Start date : " + contractDto.startDate());
            System.out.println("Planned end date : " + contractDto.plannedEndDate());
            endDate = ihmUtilsGet.getFormattedDate(scanner, "Date de fin du contrat (format: dd/MM/yyyy) : ");

            if (contractDto.startDate().before(endDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(contractDto.signatureDate(), endDate)
                    && dateUtils.isFirstDateBeforeOrSameSecondDate(contractDto.startDate(), endDate)) {
                validDates = true;
            } else {
                System.out.println("Dates are not valid. End date must be after signature and start date.");
            }
        } while (!validDates);

        WorkingConditions workingConditions = ihmUtilsGet.getWorkingConditions(scanner);
        TerminationReason terminationReason = ihmUtilsGet.getTerminationReason(scanner);

        return new ContractDto(contractDto.id(), title, contractDto.signatureDate(), contractDto.startDate(), endDate, contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), terminationReason, workingConditions, contractDto.employeeId(),  contractDto.jobId()/*, contractDto.amendments()*/);
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

        return new ContractDto(contract.getId(), title, signatureDate, startDate, null, plannedEndDate, salary, type, null, workingConditions, contract.getEmployee().getId(),contract.getJob().getId()/*, new ArrayList<>()*/);
    }

    public UserDto createUserDto(Scanner scanner) {
        String firstname = ihmUtilsGet.getUserInput(scanner, "Prénom : ");
        String lastname = ihmUtilsGet.getUserInput(scanner, "NOM : ");
        Date birthDate = ihmUtilsGet.getFormattedDate(scanner, "Date de naissance (format: dd/MM/yyyy) : ");
        String email = ihmUtilsGet.getUserInput(scanner, "Email : ");
        String password = ihmUtilsGet.getUserInput(scanner, "Mot de passe :");

        return new UserDto(null, firstname, lastname, birthDate, email, password, AppRole.USER);
    }

    public UserDto updateUserDto(Scanner scanner, UserDto userDto){
        UserDto usrDto = createUserDto(scanner);
        return new UserDto(userDto.id(), usrDto.firstname(), usrDto.lastname(), usrDto.birthDate(), usrDto.email(), usrDto.password(), usrDto.role());
    }

    public UserDto adminUpdateUserDto(Scanner scanner, UserDto userDto){
        UserDto usrDto = createUserDto(scanner);
        AppRole role = ihmUtilsGet.getAppRole(scanner);
        return new UserDto(userDto.id(), usrDto.firstname(), usrDto.lastname(), usrDto.birthDate(), usrDto.email(), usrDto.password(), usrDto.role());
    }

    public IHMUtilsSet(IHMUtilsGet ihmUtilsGet, DateUtils dateUtils) {
        this.ihmUtilsGet = ihmUtilsGet;
        this.dateUtils = dateUtils;
    }

}
