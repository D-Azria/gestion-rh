package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.entities.Contract;
import org.springframework.stereotype.Controller;

@Controller
public abstract class ContractController extends AbstractFacadeController<Contract, ContractDto>{
}
