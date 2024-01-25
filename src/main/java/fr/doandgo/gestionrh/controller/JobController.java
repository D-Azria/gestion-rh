package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.JobDto;
import fr.doandgo.gestionrh.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job")
public class JobController extends AbstractFacadeController<JobDto> {

    private final JobService jobService;

    @GetMapping("/getByCompany/{companyId}")
    public List<JobDto> getAllJobsByCompanyId(Integer companyId){
        return jobService.getAllJobsByCompanyId(companyId);
    }

    @GetMapping("/getNoContractsByCompany/{companyId}")
    public List<JobDto> getAllJobsWithoutContractByCompanyId(Integer companyId){
        return jobService.getAllJobsWithoutContractByCompanyId(companyId);
    }

    @Override
    @GetMapping("/getall")
    public List<JobDto> getAll() {
        return jobService.getAll();
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<JobDto> getById(Integer id) {
        return ResponseEntity.status(200).body(jobService.getById(id));
    }

    @Override
    @PostMapping("/create")
    public void create(JobDto jobDto) {
        jobService.create(jobDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(Integer id) {
        jobService.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    public void update(JobDto jobDto) {
        jobService.update(jobDto);
    }

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
}
