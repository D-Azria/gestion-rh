package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.entities.Company;
import fr.doandgo.gestionrh.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> getById(Integer id){
        return companyRepository.findById(id);
    }


}
