package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Employee;
import org.springframework.stereotype.Controller;

@Controller
public abstract class EmployeeController extends AbstractFacadeController<Employee, EmployeeDto> {
}
