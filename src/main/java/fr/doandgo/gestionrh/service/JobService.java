package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class JobService extends JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public void create(JobDto jobDto) {
        Job newJob = jobDtoToEntity(jobDto);
        if (jobDto.companyId() != 0) {
            Company company = companyService.getById(jobDto.companyId());
            if (company != null) {
                company.getJobs().add(newJob);
                newJob.setCompany(company);
            }
        }
        jobRepository.save(newJob);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public List<Job> getAllJobsByCompanyId(Integer companyId) {
        List<Job> jobs = jobRepository.findAllByCompanyId(companyId);
        return jobs;
    }

    public Job getById(Integer jobId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Job not found"));
        } else {
            return job.get();
        }
    }

    public void deleteById(Integer jobId) {
        jobRepository.deleteById(jobId);
    }

    @Transactional
    public void update(JobDto jobDto) {
        Job jobToUpdate = getById(jobDto.id());

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
                Company company = companyService.getById(jobDto.companyId());
                if (company != null) {
                    company.getJobs().add(jobToUpdate);
                    jobToUpdate.setCompany(company);
                }
            }
            jobRepository.save(jobToUpdate);
        }
    }

    public Job jobDtoToEntity(JobDto jobDto) {
        return new Job(
                jobDto.name(),
                jobDto.service(),
                jobDto.category(),
                companyService.getById(
                        jobDto.companyId()),
                null,
                null);
    }

    public JobDto jobEntityToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getName(),
                job.getService(),
                job.getCategory(),
                job.getCompany().getId()
        );
    }


}
