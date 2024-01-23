package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
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
    private IHMUtilsGet ihmUtilsGet;
    @Autowired
    private Stylized3LText stylized3LText;
    @Autowired
    private Stylized4LText stylized4LText;

    public void jobsMenu(Scanner scanner) {
        displayJobsMenu();
        String chosenJobMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenJobMenuItem);
        jobMainSwitch(choice, scanner);
    }

    private void jobMainSwitch(Integer choice, Scanner scanner) {
        switch (choice) {
            case 1:
                stylized3LText.listJobs();
                listJobs();
                break;
            case 2:
                stylized3LText.createJob();
                createJob(scanner);
                break;
            case 3:
                stylized3LText.updateJob();
                updateJob(scanner);
                break;
            case 4:
                stylized3LText.deleteJob();
                deleteJob(scanner);
                break;
        }
    }

    public List<Job> listJobs() {
        List<Job> jobs = jobController.getAll();
        for (Job job : jobs) {
            System.out.println("Id: " + job.getId() + ", name: " + job.getName());
        }
        return jobs;
    }

    private void createJob(Scanner scanner) {
        System.out.println("Créer une fiche de poste : ");
        //TODO : gestion des erreurs
        System.out.print("Nom :");
        String jobName = scanner.nextLine();
        Service service = ihmUtilsGet.getSelectedService(scanner);
        Category category = ihmUtilsGet.getSelectedCategory(scanner);
        Company company = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        JobDto jobDto = new JobDto(0, jobName, service, category, company.getId());
        jobController.create(jobDto);
    }

    private void updateJob(Scanner scanner) {
        List<Job> jobs = listJobs();
        System.out.print("Modifier la fiche de poste n° : ");
        String jobToUpdate = scanner.nextLine();
        Integer jobIdToUpdate = Integer.parseInt(jobToUpdate);
        Optional<Job> selectedJob = jobs.stream().filter(j -> Objects.equals(j.getId(), jobIdToUpdate)).findFirst();
        if (selectedJob.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            System.out.println("Ancien nom : " + selectedJob.get().getName());
            System.out.print("Nouveau nom : ");
            String updatedJobName = scanner.nextLine();
            System.out.println("Ancien service : " + selectedJob.get().getService());
            Service updatedService = ihmUtilsGet.getSelectedService(scanner);
            System.out.println("Ancienne catégorie : " + selectedJob.get().getCategory());
            Category updatedCategory = ihmUtilsGet.getSelectedCategory(scanner);
            System.out.println("Ancienne entreprise : " + selectedJob.get().getCompany().getName());
            Company updatedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());

            JobDto updatedJobDto = new JobDto(selectedJob.get().getId(), updatedJobName, updatedService, updatedCategory, updatedCompany == null ? 0 : updatedCompany.getId());

            jobController.update(updatedJobDto);
        }
    }

    private void deleteJob(Scanner scanner) {
        listJobs();
        System.out.print("Supprimer la fiche de poste n° :");
        String job = scanner.nextLine();
        Integer jobId = Integer.parseInt(job);
        jobController.deleteById(jobId);
    }

    private void displayJobsMenu() {
        stylized4LText.job();
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
