package fr.doandgo.gestionrh.controller;

import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.entities.Company;
import org.springframework.stereotype.Controller;


@Controller
public abstract class CompanyController extends AbstractFacadeController<Company, CompanyDto> {

}
