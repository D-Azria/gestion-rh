package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.EmployeeDto;
import fr.doandgo.gestionrh.dto.MessageDto;
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
public class EmployeeServiceImpl implements EmployeeService {

    private final ContractService contractService;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public EmployeeDto getById(Integer id) {
        return entityToDto(getEmployeeById(id));
    }
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Employee with id:" + id + " not found!"));
        } else {
            return employee.get();
        }
    }


    public List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId) {
        List<ContractDto> contractsOfCompany = contractService.getAllContractsByCompanyId(companyId);
        Set<Integer> employeesId = new HashSet<>();
        for (ContractDto c : contractsOfCompany) {
            System.out.println(c.title());
            employeesId.add(c.employeeId());
        }
        List<Employee> employees = new ArrayList<>();
        for (Integer id : employeesId) {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isEmpty()) {

            } else {
                employees.add(employee.get());
            }
        }
        return employees.stream().map(this::entityToDto).toList();
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
        Employee employeeToUpdate = dtoToEntity(getById(employeeDto.id()));

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
            if(employeeDto.contractIds() != null && !employeeDto.contractIds().isEmpty()){
                List<Contract> contracts = employeeDto.contractIds().stream()
                        .map(contractService::getContractById)
                        .toList();

                employeeToUpdate.getContracts().addAll(contracts);
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
        List<Contract> contracts = employeeDto.contractIds().stream()
                .map(contractService::getContractById)
                .toList();

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
                    contracts
            );
        }
    }

    @Override
    public EmployeeDto entityToDto(Employee employee) {
        List<Integer> contractIds = employee.getContracts().stream()
                .map(Contract::getId)
                .toList();

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getBirthDate(),
                employee.getDiplomas(),
                contractIds,
                null
        );
    }
}
