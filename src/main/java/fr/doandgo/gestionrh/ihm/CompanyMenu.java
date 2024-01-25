package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CompanyMenu {

    private final CompanyController companyController;
    private final IHMUtilsGet ihmUtilsGet;
    private final Stylized3LText stylized3LText;
    private final Stylized4LText stylized4LText;

    public void companyMenu(Scanner scanner) {
        displayCompanyMenu();
        String chosenCompanyMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenCompanyMenuItem);
        companyMainSwitch(choice, scanner);
    }

    private void companyMainSwitch(Integer choice, Scanner scanner) {
        switch (choice) {
            case 1:
                stylized3LText.listCompanies();
                lisCompanies();
                break;
            case 2:
                stylized3LText.createCompany();
                createCompany(scanner);
                break;
            case 3:
                stylized3LText.updateCompany();
                updateCompany(scanner);
                break;
            case 4:
                stylized3LText.deleteCompany();
                deleteCompany(scanner);
                break;
        }
    }

    private List<CompanyDto> lisCompanies() {
        List<CompanyDto> companies = companyController.getAll();
        for (CompanyDto company : companies) {
            System.out.println("Id: " + company.id() + ", name: " + company.name());
        }
        return companies;
    }

    private void createCompany(Scanner scanner) {
        System.out.print("Nom :");
        String companyName = scanner.nextLine();
        System.out.print("URL :");
        String companyURL = scanner.nextLine();
        CompanyDto companyDto = new CompanyDto(0, companyName, companyURL, null);
        companyController.create(companyDto);
    }

    private void updateCompany(Scanner scanner) {
        List<CompanyDto> companies = lisCompanies();

        Integer companyIdToUpdate = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Id de l'entreprise à modifier :"));
        Optional<CompanyDto> selectedCompany = companies.stream().filter(comp -> Objects.equals(comp.id(), companyIdToUpdate)).findFirst();
        if (selectedCompany.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            System.out.println("Ancien nom : " + selectedCompany.get().name());
            String updatedCompanyName = ihmUtilsGet.getUserInput(scanner, "Nouveau nom :");
            System.out.println("Ancienne URL : " + selectedCompany.get().url());
            String updatedCompanyURL = ihmUtilsGet.getUserInput(scanner,"Nouvelle URL :");
            CompanyDto updatedCompanyDto = new CompanyDto(selectedCompany.get().id(), updatedCompanyName, updatedCompanyURL, null);
            companyController.update(updatedCompanyDto);
        }
    }

    private void deleteCompany(Scanner scanner) {
        lisCompanies();
        Integer companyId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Id de l'entreprise à supprimer :"));
        companyController.deleteById(companyId);
    }

    private void displayCompanyMenu() {
        stylized4LText.company();
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les entreprises");
        System.out.println("2. Créer une entreprise");
        System.out.println("3. Modifier une entreprise");
        System.out.println("4. Supprimer une entreprise");
        System.out.println("");
        System.out.println("Autres touches : retour au menu principal");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    public CompanyMenu(CompanyController companyController, IHMUtilsGet ihmUtilsGet, Stylized3LText stylized3LText, Stylized4LText stylized4LText) {
        this.companyController = companyController;
        this.ihmUtilsGet = ihmUtilsGet;
        this.stylized3LText = stylized3LText;
        this.stylized4LText = stylized4LText;
    }
}
