package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController extends AbstractFacadeController<EmployeeDto> {

    private final EmployeeService employeeService;

    @GetMapping("/company/{companyId}")
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId) {
        return employeeService.getAllEmployeesByCompanyId(companyId);
    }

    @Override
    @GetMapping("/getall")
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable  Integer id) {
        return ResponseEntity.status(200).body(employeeService.getById(id));
    }

    @Override
    @PostMapping("/create")
    public void create(EmployeeDto employeeDto) {
        employeeService.create(employeeDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(Integer id) {
        employeeService.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    public void update(EmployeeDto employeeDto) {
        employeeService.update(employeeDto);
    }

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
