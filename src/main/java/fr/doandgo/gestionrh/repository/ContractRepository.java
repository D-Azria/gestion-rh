package fr.doandgo.gestionrh.repository;

import fr.doandgo.gestionrh.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {


    List<Contract> findContractsByJob_Company_Id(Integer id);

    List<Contract> findContractsByEmployeeId(Integer id);

}
