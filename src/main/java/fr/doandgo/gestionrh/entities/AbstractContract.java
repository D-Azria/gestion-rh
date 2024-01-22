package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.TerminationReason;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractContract extends AbstractBaseEntity{

    @NotNull
    private String title;
    @NotNull
    private Date signatureDate;
    @NotNull
    private Date startDate;

    private Date endDate;

    private Date plannedEndDate;
    @NotNull
    private Double salary;
    @NotNull
    private ContractTypes type;

    private TerminationReason terminationReason;
    @NotNull
    private WorkingConditions workingConditions;

    @ManyToOne
    @NotNull
    private Employee employee;

    @ManyToOne
    @NotNull
    private Job job;
}
