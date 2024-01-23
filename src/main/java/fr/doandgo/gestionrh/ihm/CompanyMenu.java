package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CompanyMenu {

    @Autowired
    private CompanyController companyController;
    @Autowired
    private Stylized3LText stylized3LText;
    @Autowired
    private Stylized4LText stylized4LText;

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

    private List<Company> lisCompanies() {
        List<Company> companies = companyController.getAll();
        // List all companies
        System.out.println("--  Liste des entreprises  --");
        for (Company company : companies) {
            System.out.println("Id: " + company.getId() + ", name: " + company.getName());
        }
        return companies;
    }

    private void createCompany(Scanner scanner) {
        // Create a company
        System.out.println("Créer une entreprise :");
        //TODO : gestion des erreurs
        System.out.print("Nom :");
        String companyName = scanner.nextLine();
        System.out.print("URL :");
        String companyURL = scanner.nextLine();
        CompanyDto companyDto = new CompanyDto(0, companyName, companyURL);
        companyController.create(companyDto);
    }

    private void updateCompany(Scanner scanner) {
        List<Company> companies = lisCompanies();
        System.out.print("Id de l'entreprise à modifier :");
        //TODO : gestion des erreurs
        String companyToUpdate = scanner.nextLine();
        Integer companyIdToUpdate = Integer.parseInt(companyToUpdate);
        Optional<Company> selectedCompany = companies.stream().filter(comp -> Objects.equals(comp.getId(), companyIdToUpdate)).findFirst();
        if (selectedCompany.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            System.out.println("Ancien nom : " + selectedCompany.get().getName());
            System.out.print("Nouveau nom :");
            String updatedCompanyName = scanner.nextLine();
            System.out.println("Ancienne URL : " + selectedCompany.get().getUrl());
            System.out.print("Nouvelle URL :");
            String updatedCompanyURL = scanner.nextLine();
            CompanyDto updatedCompanyDto = new CompanyDto(selectedCompany.get().getId(), updatedCompanyName, updatedCompanyURL);
            companyController.update(updatedCompanyDto);
        }
    }

    private void deleteCompany(Scanner scanner) {
        lisCompanies();
        System.out.print("Id de l'entreprise à supprimer :");
        //TODO : gestion des erreurs
        String company = scanner.nextLine();
        Integer companyId = Integer.parseInt(company);
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
        System.out.println("0. Retour");
        System.out.println("99. Quitter");
        System.out.println("");
        System.out.print("Choix n° ");
    }

}
