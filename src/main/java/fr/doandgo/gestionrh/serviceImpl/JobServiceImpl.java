package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.JobRepository;
import fr.doandgo.gestionrh.service.CompanyService;
import fr.doandgo.gestionrh.service.JobService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final CompanyService companyService;

    public List<JobDto> getAll() {
        return jobRepository.findAll().stream().map(this::jobEntityToDto).toList();
    }

    public List<JobDto> getAllJobsByCompanyId(Integer companyId) {
        return jobRepository.findAllByCompanyId(companyId).stream().map(this::jobEntityToDto).toList();
    }

    public List<JobDto> getAllJobsWithoutContractByCompanyId(Integer companyId) {
        return jobRepository.findByCompanyIdAndContractsIsEmpty(companyId).stream().map(this::jobEntityToDto).toList();
    }

    public JobDto getById(Integer jobId) {
        return jobEntityToDto(getJobEntityById(jobId));
    }

    public Job getJobEntityById(Integer jobId){
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Job not found"));
        } else {
            return job.get();
        }
    }

    @Transactional
    public void create(JobDto jobDto) {
        Job newJob = jobDtoToEntity(jobDto);
        if (jobDto.companyId() != 0) {
            Company company = companyService.dtoToEntity(companyService.getById(jobDto.companyId()));
            if (company != null) {
                company.getJobs().add(newJob);
                newJob.setCompany(company);
            }
        }
        jobRepository.save(newJob);
    }

    @Transactional
    public void update(JobDto jobDto) {
        Job jobToUpdate = getJobEntityById(jobDto.id());

        if (jobToUpdate != null) {

            if (jobDto.name() != null && !jobDto.name().isEmpty()) {
                jobToUpdate.setName(jobDto.name());
            }
            if (jobDto.service() != null) {
                jobToUpdate.setService(jobDto.service());
            }
            if (jobDto.category() != null) {
                jobToUpdate.setCategory(jobDto.category());
            }
            if (jobDto.companyId() != 0) {
                Company company = companyService.dtoToEntity(companyService.getById(jobDto.companyId()));
                if (company != null) {
                    company.getJobs().add(jobToUpdate);
                    jobToUpdate.setCompany(company);
                }
            }
            if (jobDto.managerId() != 0){
                Job manager = getJobEntityById(jobDto.managerId());
                manager.addManagedJob(jobToUpdate);
                jobToUpdate.setManager(manager);
            }
            jobRepository.save(jobToUpdate);
        }
    }

    @Transactional
    public void deleteById(Integer jobId) {
        jobRepository.deleteById(jobId);
    }


    public Job jobDtoToEntity(JobDto jobDto) {
        return new Job(
                jobDto.name(),
                jobDto.service(),
                jobDto.category(),
                companyService.dtoToEntity(companyService.getById(
                        jobDto.companyId())),
                null,
                (jobDto.jobsManagedIds() != null) ? jobDto.jobsManagedIds().stream()
                        .map(this::getJobEntityById)
                        .toList() : new ArrayList<>(),
                (jobDto.managerId() != null) ? jobDtoToEntity(getById(jobDto.managerId())) : null);
    }

    public JobDto jobEntityToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getName(),
                job.getService(),
                job.getCategory(),
                job.getCompany().getId(),
                (job.getJobsManaged() != null) ? job.getJobsManaged().stream()
                        .map(Job::getId)
                        .toList() : new ArrayList<>(),
                (job.getManager() != null) ? job.getManager().getId() : null
        );
    }

    public JobServiceImpl(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }
}
