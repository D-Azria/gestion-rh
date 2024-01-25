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

    List<JobDto> getAll();

    List<JobDto> getAllJobsByCompanyId(Integer companyId);

    List<JobDto> getAllJobsWithoutContractByCompanyId(Integer companyId);

    JobDto getById(Integer jobId);

    Job getJobEntityById(Integer jobId);

    @Transactional
    void update(JobDto jobDto);

    @Transactional
    void deleteById(Integer jobId);

    Job jobDtoToEntity(JobDto jobDto);

    JobDto jobEntityToDto(Job job);


}
