package fr.doandgo.gestionrh.repository;

import fr.doandgo.gestionrh.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
