package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.entities.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface EmployeeService  {

   List<EmployeeDto> getAll();

   EmployeeDto getById(Integer id) ;

   List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId);

   @Transactional
   void create(EmployeeDto employeeDto);

   void deleteById(Integer id);

   void update(EmployeeDto employeeDto) ;

   Employee dtoToEntity(EmployeeDto employeeDto);

   EmployeeDto entityToDto(Employee employee);
}
