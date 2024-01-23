package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.entities.Contract;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public abstract class ContractController extends AbstractFacadeController<Contract, ContractDto>{

    public abstract List<Contract> getAllContractsByCompanyId(Integer companyId);
    public abstract List<Contract> getAllContractsByEmployeeId(Integer employeeId);


}
