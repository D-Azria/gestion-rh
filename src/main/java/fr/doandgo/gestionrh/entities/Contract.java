package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contract extends AbstractContract {

    @OneToMany
    private List<Amendments> amendments;

    public Contract(String title, Date signatureDate, Date startDate, Date plannedEndDate, Double salary, ContractTypes type,  WorkingConditions workingConditions, Job job) {
        super.setTitle(title);
        super.setSignatureDate(signatureDate);
        super.setStartDate(startDate);
        super.setPlannedEndDate(plannedEndDate);
        super.setSalary(salary);
        super.setType(type);
        super.setWorkingConditions(workingConditions);
        super.setJob(job);
    }

    public Contract(String title, Date signatureDate, Date startDate, Date plannedEndDate, Double salary, ContractTypes type, WorkingConditions workingConditions, Employee employee, Job job, List<Amendments> amendments) {
        super.setTitle(title);
        super.setSignatureDate(signatureDate);
        super.setStartDate(startDate);
        super.setPlannedEndDate(plannedEndDate);
        super.setSalary(salary);
        super.setType(type);
        super.setWorkingConditions(workingConditions);
        super.setEmployee(employee);
        super.setJob(job);
        this.amendments = amendments;
    }
}
