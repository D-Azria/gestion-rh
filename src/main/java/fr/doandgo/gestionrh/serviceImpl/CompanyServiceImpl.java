package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.dto.CompanyDto;
import fr.doandgo.gestionrh.dto.MessageDto;
import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.repository.CompanyRepository;
import fr.doandgo.gestionrh.service.CompanyService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompanyDto> getAll() {
        return companyRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public CompanyDto getById(Integer id) {
        return entityToDto(getCompanyById(id));
    }


    public Company getCompanyById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Company not found"));
        } else {
            return company.get();
        }
    }

    @Transactional
    public void create(CompanyDto companyDto) {
        companyRepository.save(dtoToEntity(companyDto));
    }

    @Transactional
    public void update(CompanyDto companyDto) {
        Company companyToUpdate = getCompanyById(companyDto.id());
        if (!companyDto.name().isEmpty()) {
            companyToUpdate.setName(companyDto.name());
        }
        if (!companyDto.url().isEmpty()) {
            companyToUpdate.setUrl(companyDto.url());
        }
        companyRepository.save(companyToUpdate);
    }

    @Transactional
    public void deleteById(Integer companyId) {
        companyRepository.deleteById(companyId);
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
                    companyDto.url(),
                    new ArrayList<>()
            );
        }
    }

    public CompanyDto entityToDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getUrl(),
                company.getJobs().stream().map(Job::getId).toList()
        );
    }

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
