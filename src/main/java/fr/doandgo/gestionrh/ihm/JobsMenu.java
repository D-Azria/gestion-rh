package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class JobsMenu {

    @Autowired
    private JobController jobController;
    @Autowired
    private CompanyController companyController;
    @Autowired
    private IHMUtils ihmUtils;
    public void JobsMenu(Scanner scanner) {

        displayJobsMenu();
        String chosenJobMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenJobMenuItem);
        jobMainSwitch(choice, scanner);
    }

    private void jobMainSwitch(Integer choice, Scanner scanner) {
        List<Job> jobs = jobController.getAll();
        switch (choice) {
            case 1:
                System.out.println("--  Liste des fiches de poste  --");
                for (Job j : jobs) {
                    System.out.println("Id: " + j.getId() + ", name: " + j.getName());
                }
                break;
            case 2:
                System.out.println("Créer une fiche de poste :");
                //TODO : gestion des erreurs
                System.out.print("Nom :");
                String jobName = scanner.nextLine();
                Service service = ihmUtils.getSelectedService(scanner);
                Category category = ihmUtils.getSelectedCategory(scanner);
                Company company = ihmUtils.getSelectedCompany(scanner, companyController.getAll());
                JobDto jobDto = new JobDto(0,jobName,service, category, company.getId());
                jobController.create(jobDto);
                break;
            case 3:
                System.out.println("--  Liste des fiches de poste  --");
                for (Job j : jobs) {
                    System.out.println("Id: " + j.getId() + ", name: " + j.getName());
                }
                System.out.print("Id de la fiche de poste à modifier :");
                //TODO : gestion des erreurs
                String jobToUpdate = scanner.nextLine();
                Integer jobIdToUpdate = Integer.parseInt(jobToUpdate);
                Optional<Job> selectedJob = jobs.stream().filter(j -> Objects.equals(j.getId(), jobIdToUpdate)).findFirst();
                if (selectedJob.isEmpty()) {
                    throw new NotFoundOrValidException(new MessageDto("Company not found"));
                } else {
                    System.out.println("Ancien nom : " + selectedJob.get().getName());
                    System.out.print("Nouveau nom :");
                    String updatedJobName = scanner.nextLine();
                    System.out.println("Ancien service : " + selectedJob.get().getService());
                    Service updatedService = ihmUtils.getSelectedService(scanner);
                    System.out.println("Ancienne catégorie : " + selectedJob.get().getCategory());
                    Category updatedCategory = ihmUtils.getSelectedCategory(scanner);
                    System.out.println("Ancienne entreprise : " + selectedJob.get().getCompany().getName());
                    Company updatedCompany = ihmUtils.getSelectedCompany(scanner, companyController.getAll());

                    JobDto updatedJobDto = new JobDto(selectedJob.get().getId(),updatedJobName,updatedService, updatedCategory, updatedCompany == null ? 0 : updatedCompany.getId());

                    jobController.update(updatedJobDto);
                }
                break;
            case 4:
                System.out.println("--  Liste des fiches de poste  --");
                for (Job j : jobs) {
                    System.out.println("Id: " + j.getId() + ", name: " + j.getName());
                }
                System.out.print("Id de la fiche de poste à supprimer :");
                //TODO : gestion des erreurs
                String job = scanner.nextLine();
                Integer jobId = Integer.parseInt(job);
                jobController.deleteById(jobId);
                break;
        }
    }

    private void displayJobsMenu() {
        System.out.println("________________________________________________________________________________________");
        System.out.println("     _   ___   ___  ___    _____________________________________________________________");
        System.out.println("  _ | | / _ \\ | _ )/ __|");
        System.out.println(" | || || (_) || _ \\__ \\                                  ******* Fiches de poste *******");
        System.out.println("  \\__/  \\___/ |___/|___/");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les offres");
        System.out.println("2. Créer une fiche de poste");
        System.out.println("3. Modifier une fiche de poste");
        System.out.println("4. Supprimer une fiche de poste");
        System.out.println("0. Retour au menu principal");
        System.out.println("99. Quitter");
    }
}
