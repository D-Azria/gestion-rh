package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.dto.ContractDto;
import fr.doandgo.gestionrh.entities.Contract;
import fr.doandgo.gestionrh.service.ContractService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DailyProcessing {

    private final ContractService contractService;

    @Scheduled(cron = "0 19 15 * * *", zone = "Europe/Paris")
    /*
    * Execute at 01:00 am, CET
    * - 0 : seconds
    * - 0 : minutes
    * - 1 : hours (h24)
    * - * : days in month (* = each days, 15 for 15th of the month)
    * - * : month (* = each month, 8 for august)
    * - * : days in week (* = each day of the week, MON for monday)
    * - zone = "Europe/Paris" : CET
    */
    private void endingContractsAtPlannedEnd(){
        System.out.println();
       System.out.println("DailyProcessing : endingContractsAtPlannedEnd");
       List<ContractDto> contracts = contractService.getALlEndingContractsAtPlannedEnd();
       for (ContractDto contract : contracts){
           contractService.update(new ContractDto(contract.id(), contract.title(), contract.signatureDate(), contract.startDate(), new Date(), contract.plannedEndDate(), contract.salary(), contract.type(), contract.terminationReason(),  contract.workingConditions(), contract.employeeId(),contract.jobId()/*, contract.amendments()*/));
       }
    }

    public DailyProcessing(ContractService contractService) {
        this.contractService = contractService;
    }
}
