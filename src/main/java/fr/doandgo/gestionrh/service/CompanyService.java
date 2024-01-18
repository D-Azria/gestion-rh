package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(Integer id){
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            return company.get();
        }
    }

    public void create(CompanyDto companyDto) {
        companyRepository.save(dtoToEntity(companyDto));
    }

    public void deleteById(Integer companyId) {
        companyRepository.deleteById(companyId);
    }

    public void update(CompanyDto companyDto) {
    }


    public Company dtoToEntity(CompanyDto companyDto) {
        return new Company(
                companyDto.name(),
                companyDto.url(),
                null
        );
    }

    public CompanyDto entityToDto(Company company) {
        return new CompanyDto(
                company.getName(),
                company.getUrl()
        );
    }}
