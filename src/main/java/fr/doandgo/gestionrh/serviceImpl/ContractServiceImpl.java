package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.controller.ContractController;
import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.ContractRepository;
import fr.doandgo.gestionrh.service.ContractService;
import fr.doandgo.gestionrh.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl extends ContractController implements ContractService  {

    private final ContractRepository contractRepository;

    private final EmployeeService employeeService;

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract getById(Integer id) {
        Optional<Contract> contract = contractRepository.findById(id);
        if (contract.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Contract not found"));
        } else {
            return contract.get();
        }
    }

    @Override
    public List<Contract> getAllContractsByCompanyId(Integer companyId){
        return contractRepository.findContractsByJob_Company_Id(companyId);
    }

    @Override
    public void create(ContractDto contractDto) {
        contractRepository.save(new Contract(contractDto.title(),contractDto.signatureDate(), contractDto.startDate(), contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), contractDto.workingConditions(), employeeService.getById(contractDto.employeeId()), contractDto.job(), contractDto.amendments()));
    }

    @Transactional
    public Contract createFirstContract(ContractDto contractDto, Employee employee) {
        Contract contract = new Contract(contractDto.title(),contractDto.signatureDate(), contractDto.startDate(), contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), contractDto.workingConditions(), contractDto.job()) ;
        contract.setEmployee(employee);
        return contractRepository.save(contract);
    }

    @Override
    public void deleteById(Integer id) {
        contractRepository.deleteById(id);
    }

    @Override
    public void update(ContractDto contractDto) {

    }

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, @Lazy EmployeeService employeeService) {
        this.contractRepository = contractRepository;
        this.employeeService = employeeService;
    }
}
