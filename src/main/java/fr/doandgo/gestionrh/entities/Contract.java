package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.TerminationReason;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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
public class Contract extends AbstractBaseEntity{

    @NotNull
    private String title;
    @NotNull
    private Date signatureDate;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    private Date plannedEndDate;
    @NotNull
    private Double salary;
    @NotNull
    private ContractTypes type;
    @NotNull
    private TerminationReason terminationReason;
    @NotNull
    private WorkingConditions workingConditions;

    @ManyToOne
    @NotNull
    private Employee employee;

    @ManyToOne
    @NotNull
    private Job job;

    @OneToMany
    private List<Amendments> amendments;

    public Contract(String title, Date signatureDate, Date startDate, Date endDate, Date plannedEndDate, Double salary, ContractTypes type, TerminationReason terminationReason, WorkingConditions workingConditions, Job job) {
        this.title = title;
        this.signatureDate = signatureDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plannedEndDate = plannedEndDate;
        this.salary = salary;
        this.type = type;
        this.terminationReason = terminationReason;
        this.workingConditions = workingConditions;
        this.job = job;
    }
}
