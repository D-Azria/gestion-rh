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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl extends ContractController implements ContractService {

    private final ContractRepository contractRepository;

    private final EmployeeService employeeService;

    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    public Contract getById(Integer id) {
        Optional<Contract> contract = contractRepository.findById(id);
        if (contract.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Contract not found"));
        } else {
            return contract.get();
        }
    }

    public List<Contract> getAllContractsByCompanyId(Integer companyId) {
        return contractRepository.findContractsByJob_Company_Id(companyId);
    }


    public List<Contract> getAllContractsByEmployeeId(Integer employeeId) {
        return contractRepository.findContractsByEmployeeId(employeeId);
    }

    @Transactional
    public void create(ContractDto contractDto) {
        contractRepository.save(new Contract(contractDto.title(), contractDto.signatureDate(), contractDto.startDate(), contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), contractDto.workingConditions(), employeeService.getById(contractDto.employeeId()), contractDto.job(), contractDto.amendments()));
    }

    @Transactional
    public Contract createFirstContract(ContractDto contractDto, Employee employee) {
        Contract contract = new Contract(contractDto.title(), contractDto.signatureDate(), contractDto.startDate(), contractDto.plannedEndDate(), contractDto.salary(), contractDto.type(), contractDto.workingConditions(), contractDto.job());
        contract.setEmployee(employee);
        return contractRepository.save(contract);
    }

    @Transactional
    public void update(ContractDto contractDto) {
        Contract contractToUpdate = getById(contractDto.id());

        if(contractToUpdate != null){
            if(contractDto.title() != null && !contractDto.title().isEmpty()){
                contractToUpdate.setTitle(contractDto.title());
            }
            if(contractDto.signatureDate() != null){
                contractToUpdate.setSignatureDate(contractDto.signatureDate());
            }
            if(contractDto.startDate() != null){
                contractToUpdate.setStartDate(contractDto.startDate());
            }
            if(contractDto.plannedEndDate() != null){
                contractToUpdate.setPlannedEndDate(contractDto.plannedEndDate());
            }
            if(contractDto.endDate() != null){
                contractToUpdate.setEndDate(contractDto.endDate());
            }
            if(contractDto.salary() != null && !contractDto.salary().isNaN() && !contractDto.salary().isInfinite()){
                contractToUpdate.setSalary(contractDto.salary());
            }
            if(contractDto.type() != null){
                contractToUpdate.setType(contractDto.type());
            }
            if(contractDto.workingConditions() != null ){
                contractToUpdate.setWorkingConditions(contractDto.workingConditions());
            }
            if(contractDto.job() != null){
                contractToUpdate.setJob(contractDto.job());
            }
            if(contractDto.employee() != null) {
                contractToUpdate.setEmployee(contractDto.employee());
            } else if(contractDto.employeeId() != null){
                contractToUpdate.setEmployee(employeeService.getById(contractDto.employeeId()));
            }
            contractRepository.save(contractToUpdate);
        }
    }


    @Transactional
    public void deleteById(Integer id) {
        contractRepository.deleteById(id);
    }


    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, @Lazy EmployeeService employeeService) {
        this.contractRepository = contractRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Contract dtoToEntity(ContractDto contractDto) {
        if (contractDto.id() == null || contractDto.id() == 0) {
            return new Contract(
                    contractDto.title(),
                    contractDto.signatureDate(),
                    contractDto.startDate(),
                    contractDto.plannedEndDate(),
                    contractDto.salary(),
                    contractDto.type(),
                    contractDto.workingConditions(),
                    contractDto.job()
            );
        } else {
            return new Contract(
                    contractDto.title(),
                    contractDto.signatureDate(),
                    contractDto.startDate(),
                    contractDto.plannedEndDate(),
                    contractDto.salary(),
                    contractDto.type(),
                    contractDto.workingConditions(),
                    contractDto.employee(),
                    contractDto.job(),
                    contractDto.amendments()

            );
        }
    }

    @Override
    public ContractDto entityToDto(Contract contract) {
        return new ContractDto(
                contract.getId(),
                contract.getTitle(),
                contract.getSignatureDate(),
                contract.getStartDate(),
                contract.getEndDate(),
                contract.getPlannedEndDate(),
                contract.getSalary(),
                contract.getType(),
                contract.getTerminationReason(),
                contract.getWorkingConditions(),
                contract.getEmployee().getId(),
                contract.getEmployee(),
                contract.getJob(),
                contract.getAmendments()
        );
    }
}
