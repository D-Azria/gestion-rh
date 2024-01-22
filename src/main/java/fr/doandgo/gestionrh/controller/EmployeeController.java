package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public abstract class EmployeeController extends AbstractFacadeController<Employee, EmployeeDto> {

    @Transactional(readOnly = true)
    public abstract List<Employee> getAllEmployeesByCompanyId(Integer companyId);
}
