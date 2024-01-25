package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class JobsMenu {

    private final JobController jobController;
    private final CompanyController companyController;
    private final IHMUtilsGet ihmUtilsGet;
    private final Stylized3LText stylized3LText;
    private final Stylized4LText stylized4LText;

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
                stylized3LText.listJobs();
                listJobsOfCOmpany(scanner);
                break;
            case 3:
                stylized3LText.createJob();
                createJob(scanner);
                break;
            case 4:
                stylized3LText.updateJob();
                updateJob(scanner);
                break;
            case 5:
                stylized3LText.deleteJob();
                deleteJob(scanner);
                break;
        }
    }

    public List<JobDto> listJobs() {
        List<JobDto> jobs = jobController.getAll();
        for (JobDto job : jobs) {
            System.out.println("Id: " + job.id() + ", name: " + job.name());
        }
        return jobs;
    }

    public List<JobDto> listJobsOfCOmpany(Scanner scanner){
        CompanyDto companyDto = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());
        System.out.println("Entreprise sélectionnée : " + companyDto.name());
        List<JobDto> jobs = jobController.getAllJobsByCompanyId(companyDto.id());
        for (JobDto job : jobs) {
            System.out.println("Id: " + job.id() + ", name: " + job.name());
        }
        return jobs;
    }

    private void createJob(Scanner scanner) {
        String jobName = ihmUtilsGet.getUserInput(scanner, "Nom :");
        Service service = ihmUtilsGet.getSelectedService(scanner);
        Category category = ihmUtilsGet.getSelectedCategory(scanner);
        CompanyDto company = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());

        JobDto manager = ihmUtilsGet.getSelectedManager(scanner, company.jobIds());
        if(manager == null){
            System.out.println("Pas de manager choisi !");
        }
        JobDto jobDto = new JobDto(0, jobName, service, category, company.id(), null, manager != null ? manager.id() : null);
        jobController.create(jobDto);
    }

    private void updateJob(Scanner scanner) {
        List<JobDto> jobs = listJobs();
        Integer jobIdToUpdate = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Modifier la fiche de poste n° : "));
        Optional<JobDto> selectedJob = jobs.stream().filter(j -> Objects.equals(j.id(), jobIdToUpdate)).findFirst();
        if (selectedJob.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            System.out.println("");
            System.out.println("Ancien nom : " + selectedJob.get().name());
            String updatedJobName = ihmUtilsGet.getUserInput(scanner, "Nouveau nom : ");

            System.out.println("");
            System.out.println("Ancien service : " + selectedJob.get().service());
            Service updatedService = ihmUtilsGet.getSelectedService(scanner);

            System.out.println("");
            System.out.println("Ancienne catégorie : " + selectedJob.get().category());
            Category updatedCategory = ihmUtilsGet.getSelectedCategory(scanner);

            System.out.println("");
            System.out.println("Ancienne entreprise : " + selectedJob.get().companyId());
            CompanyDto updatedCompany = ihmUtilsGet.getSelectedCompany(scanner, companyController.getAll());

            System.out.println("");
            if(selectedJob.get().managerId() != null) {
                System.out.println("Ancien manager : " + selectedJob.get().managerId());
            } else {
                System.out.print("Pas de précédent manager. ");
            }
            JobDto updatedManager = (updatedCompany != null) ? ihmUtilsGet.getSelectedManager(scanner, updatedCompany.jobIds()) : null;
            if (updatedManager == null) {
                System.out.println("Pas de manager choisi !");
            }

            JobDto updatedJobDto = new JobDto(selectedJob.get().id(), updatedJobName, updatedService, updatedCategory, updatedCompany == null ? null : updatedCompany.id(), null, updatedManager != null ? updatedManager.id() : null);

            jobController.update(updatedJobDto);
        }
    }

    private void deleteJob(Scanner scanner) {
        listJobs();
        Integer jobId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Supprimer la fiche de poste n° :"));
        jobController.deleteById(jobId);
    }

    private void displayJobsMenu() {
        stylized4LText.job();
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister les offres");
        System.out.println("2. Lister les offres par entreprise");
        System.out.println("3. Créer une fiche de poste");
        System.out.println("4. Modifier une fiche de poste");
        System.out.println("5. Supprimer une fiche de poste");
        System.out.println("");
        System.out.println("Autres touches : retour au menu principal");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    public JobsMenu(JobController jobController, CompanyController companyController, IHMUtilsGet ihmUtilsGet, Stylized3LText stylized3LText, Stylized4LText stylized4LText) {
        this.jobController = jobController;
        this.companyController = companyController;
        this.ihmUtilsGet = ihmUtilsGet;
        this.stylized3LText = stylized3LText;
        this.stylized4LText = stylized4LText;
    }
}
