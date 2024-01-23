package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.entities.Job;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public interface JobService {

    @Transactional
    void create(JobDto jobDto);

    List<Job> getAll();

    List<Job> getAllJobsByCompanyId(Integer companyId);

    List<Job> getAllJobsWithoutContractByCompanyId(Integer companyId);

    Job getById(Integer jobId);

    @Transactional
    void update(JobDto jobDto);

    @Transactional
    void deleteById(Integer jobId);

    Job jobDtoToEntity(JobDto jobDto);

    JobDto jobEntityToDto(Job job);


}
