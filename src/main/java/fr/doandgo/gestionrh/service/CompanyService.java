package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.entities.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface CompanyService {

     List<Company> getAll();

    Company getById(Integer id);

    @Transactional
    void create(CompanyDto companyDto);

    @Transactional
     void update(CompanyDto companyDto);

    @Transactional
    void deleteById(Integer companyId);

    Company dtoToEntity(CompanyDto companyDto);

    CompanyDto entityToDto(Company company);
}
