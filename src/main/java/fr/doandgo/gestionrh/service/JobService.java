package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobService extends JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyService companyService;

    public void create(JobDto jobDto){
        jobRepository.save(jobDtoToEntity(jobDto));
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
        return null;
    }

    public void deleteById(Integer jobId){
    }

    public void update(JobDto jobDto) {

    }

    public Job jobDtoToEntity(JobDto jobDto){
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
                job.getId(), // Assuming that id is present in the BaseEntity
                job.getName(),
                job.getService(),
                job.getCategory(),
                job.getCompany().getId() // Assuming you have a getId() method in BaseEntity
        );
    }


}
