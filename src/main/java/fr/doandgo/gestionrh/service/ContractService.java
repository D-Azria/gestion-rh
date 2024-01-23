package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractService  {

     List<Contract> getAll();

     Contract getById(Integer id) ;

     List<Contract> getAllContractsByCompanyId(Integer companyId);

     void create(ContractDto contractDto) ;

     Contract createFirstContract(ContractDto contractDto, Employee employee);

    void deleteById(Integer id);

    void update(ContractDto contractDto) ;

    Contract dtoToEntity(ContractDto contractDto);

    ContractDto entityToDto(Contract contract);
}
