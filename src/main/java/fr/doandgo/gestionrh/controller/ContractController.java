package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contract")
public class ContractController extends AbstractFacadeController<ContractDto>{

    private  final ContractService contractService;

    @GetMapping("/getByCompany/{companyId}")
    public List<ContractDto> getAllContractsByCompanyId(Integer companyId){
     return contractService.getAllContractsByCompanyId(companyId);
    }

    @GetMapping("/getByEmployee/{employeeId}")
    public List<ContractDto> getAllContractsByEmployeeId(Integer employeeId){
        return contractService.getAllContractsByEmployeeId(employeeId);
    }

    @Override
    @GetMapping("/getall")
    public List<ContractDto> getAll() {
        return contractService.getAll();
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<ContractDto> getById(Integer id) {
        return ResponseEntity.status(200).body(contractService.getById(id));
    }

    @Override
    @PostMapping("/create")
    public void create(ContractDto contractDto) {
        contractService.create(contractDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(Integer id) {
        contractService.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    public void update(ContractDto contractDto) {
        contractService.update(contractDto);
    }

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }
}
