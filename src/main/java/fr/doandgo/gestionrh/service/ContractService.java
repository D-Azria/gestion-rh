package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.controller.ContractController;
import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.repository.ContractRepository;

import java.util.List;

public class ContractService extends ContractController {

    private ContractRepository contractRepository;
    private EmployeeService employeeService;

    @Override
    public List<Contract> getAll() {
        return null;
    }

    @Override
    public Contract getById(Integer id) {
        return null;
    }



    @Override
    public void create(ContractDto contractDto) {
        contractRepository.save(new Contract(contractDto.title(),contractDto.signatureDate(), contractDto.startDate(), contractDto.endDate(), contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), contractDto.terminationReason(), contractDto.workingConditions(), employeeService.getById(contractDto.employeeId()), contractDto.job(), contractDto.amendments()));
    }

    public Contract createFirstContract(ContractDto contractDto, Employee employee) {
        Contract contract = new Contract(contractDto.title(),contractDto.signatureDate(), contractDto.startDate(), contractDto.endDate(), contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), contractDto.terminationReason(), contractDto.workingConditions(),  contractDto.job()) ;
        contract.setEmployee(employee);
        return contractRepository.save(contract);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(ContractDto contractDto) {

    }
}
