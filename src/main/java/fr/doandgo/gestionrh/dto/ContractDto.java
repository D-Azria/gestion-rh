package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.entities.Amendments;
import fr.doandgo.gestionrh.entities.Employee;
import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.ContractTypes;
import fr.doandgo.gestionrh.enums.TerminationReason;
import fr.doandgo.gestionrh.enums.WorkingConditions;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record ContractDto(@NotNull String title,
                          @NotNull Date signatureDate,
                          @NotNull Date startDate,
                          Date endDate,
                          Date plannedEndDate,
                          @NotNull Double salary,
                          @NotNull ContractTypes type,
                          TerminationReason terminationReason,
                          @NotNull WorkingConditions workingConditions,
                          Integer employeeId,
                          @NotNull Job job,
                          List<Amendments> amendments) {
}
