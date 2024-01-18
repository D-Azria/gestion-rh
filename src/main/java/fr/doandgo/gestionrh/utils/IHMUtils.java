package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class IHMUtils {
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
        String companyChoice = getUserInput(scanner, "Choix de l'entreprise:");
        try {
            int companyId = Integer.parseInt(companyChoice);
            Optional<Company> selectedCompany = companies.stream().filter(comp -> Objects.equals(comp.getId(), companyId)).findFirst();
            if (selectedCompany.isEmpty()) {
                throw new NotFoundOrValidException(new MessageDto("Company not found"));
            } else {
                return selectedCompany.get();
            }
        } catch (Exception e) {
            return null;
        }
    }
}
