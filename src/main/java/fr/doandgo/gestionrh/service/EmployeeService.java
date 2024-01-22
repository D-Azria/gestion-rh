package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface EmployeeService  {

   List<Employee> getAll();

   Employee getById(Integer id) ;

   List<Employee> getAllEmployeesByCompanyId(Integer companyId);

   @Transactional
   void create(EmployeeDto employeeDto);

   void deleteById(Integer id);

   void update(EmployeeDto employeeDto) ;


}
