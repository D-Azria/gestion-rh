package fr.doandgo.gestionrh.repository;

import fr.doandgo.gestionrh.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findAllByCompanyId(Integer id);

    List<Job> findByCompanyIdAndContractsIsEmpty(Integer id);

}
