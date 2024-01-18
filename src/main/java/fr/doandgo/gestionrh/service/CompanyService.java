package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            return company.get();
        }
    }

    @Transactional
    public void create(CompanyDto companyDto) {
        companyRepository.save(dtoToEntity(companyDto));
    }

    public void deleteById(Integer companyId) {
        companyRepository.deleteById(companyId);
    }

    @Transactional
    public void update(CompanyDto companyDto) {
        Company companyToUpdate = getById(companyDto.id());
        if (!companyDto.name().isEmpty()) {
            companyToUpdate.setName(companyDto.name());
        }
        if (!companyDto.url().isEmpty()) {
            companyToUpdate.setUrl(companyDto.url());
        }
        companyRepository.save(companyToUpdate);
    }


    public Company dtoToEntity(CompanyDto companyDto) {
        if (companyDto.id() == null || companyDto.id() == 0) {
            return new Company(
                    companyDto.name(),
                    companyDto.url()
            );
        } else {
            return new Company(
                    companyDto.id(),
                    companyDto.name(),
                    companyDto.url()
            );
        }

    }

    public CompanyDto entityToDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getUrl()
        );
    }
}
