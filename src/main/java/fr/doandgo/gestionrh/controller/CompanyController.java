package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController extends AbstractFacadeController<CompanyDto> {

    private final CompanyService companyService;

    @Override
    @GetMapping("/getAll")
    public List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @Override
    @GetMapping("/{compId}")
    public ResponseEntity<CompanyDto> getById(Integer compId) {
        return ResponseEntity.status(200).body(companyService.getById(compId));
    }

    @Override
    @PostMapping("/create")
    public void create(CompanyDto companyDto) {
        companyService.create(companyDto);
    }

    @Override
    @DeleteMapping("/{compId}")
    public void deleteById(Integer compId) {
        companyService.deleteById(compId);
    }

    @Override
    @PutMapping("/{compId}")
    public void update(CompanyDto companyDto) {
        companyService.update(companyDto);
    }

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
}
