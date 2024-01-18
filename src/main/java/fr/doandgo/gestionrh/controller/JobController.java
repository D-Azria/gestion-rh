package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.entities.Job;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public abstract class JobController extends AbstractFacadeController<Job, JobDto> {

    public abstract List<Job> getAllJobsByCompanyId(Integer companyId);

}
