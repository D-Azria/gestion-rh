package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.EmployeeRepository;
import fr.doandgo.gestionrh.service.ContractService;
import fr.doandgo.gestionrh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeServiceImpl extends EmployeeController implements EmployeeService {

    private final ContractService contractService;

    private final EmployeeRepository employeeRepository;

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

    public List<Employee> getAllEmployeesByCompanyId(Integer companyId) {
        List<Contract> contractsOfCompany = contractService.getAllContractsByCompanyId(companyId);
        Set<Integer> employeesId = new HashSet<>();
        for (Contract c : contractsOfCompany) {
            System.out.println(c.getTitle());
            employeesId.add(c.getEmployee().getId());
        }
        List<Employee> employees = new ArrayList<>();
        for (Integer id : employeesId) {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isEmpty()) {

            } else {
                employees.add(employee.get());
            }
        }
        return employees;
    }

    @Transactional
    public void create(EmployeeDto employeeDto) {
        if (employeeDto.contractDto() == null) {
            throw new NotFoundOrValidException(new MessageDto("No contract found: a contract is required to add an employee"));
        }
        Employee employee = new Employee(employeeDto.firstname(), employeeDto.lastname(), employeeDto.birthDate(), employeeDto.diplomas());
        employeeRepository.save(employee);
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
        Employee employeeToUpdate = getById(employeeDto.id());

        if(employeeToUpdate != null){
            if(employeeDto.firstname() != null && !employeeDto.firstname().isEmpty()){
                employeeToUpdate.setFirstname(employeeDto.firstname());
            }
            if(employeeDto.lastname() != null && !employeeDto.lastname().isEmpty()){
                employeeToUpdate.setLastname(employeeDto.lastname());
            }
            if(employeeDto.birthDate() != null){
                employeeToUpdate.setBirthDate(employeeDto.birthDate());
            }
            if(employeeDto.contracts() != null && !employeeDto.contracts().isEmpty()){
                employeeToUpdate.setContracts(employeeDto.contracts());
            } else if (employeeDto.contractDto() != null) {
                employeeToUpdate.getContracts().add(contractService.dtoToEntity(employeeDto.contractDto()));
            }
        }
    }

    @Autowired
    public EmployeeServiceImpl(@Lazy ContractService contractService, EmployeeRepository employeeRepository) {
        super();
        this.contractService = contractService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee dtoToEntity(EmployeeDto employeeDto) {
        if (employeeDto.id() == null || employeeDto.id() == 0) {
            return new Employee(
                    employeeDto.firstname(),
                    employeeDto.lastname(),
                    employeeDto.birthDate(),
                    employeeDto.diplomas()
            );
        } else {
            return new Employee(
                    employeeDto.id(),
                    employeeDto.firstname(),
                    employeeDto.lastname(),
                    employeeDto.birthDate(),
                    employeeDto.diplomas(),
                    employeeDto.contracts()
            );
        }
    }

    @Override
    public EmployeeDto entityToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getBirthDate(),
                employee.getDiplomas(),
                employee.getContracts(),
                null
        );
    }
}
