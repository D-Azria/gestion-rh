package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.entities.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobOperations {
    Job getJobEntityById(Integer jobId);

}
