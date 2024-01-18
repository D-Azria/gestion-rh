package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService extends EmployeeController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Employee with id:" + id + " not found!"));
        } else {
            return employee.get();
        }
    }

    @Transactional
    public void create(EmployeeDto employeeDto) {
        if (employeeDto.contractDto() == null) {
            return;
        }
        Employee employee = new Employee(employeeDto.firstname(), employeeDto.lastname(), employeeDto.birthDate(), employeeDto.diplomas());

        Contract contract = contractService.createFirstContract(employeeDto.contractDto(), employee);
        employee.getContracts().add(contract);

        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(EmployeeDto employeeDto) {

    }
}
