package fr.doandgo.gestionrh.utils;

import org.springframework.stereotype.Component;

@Component
public class Stylized3LText {

    public void menu(){
        System.out.println("  ╔╦╗ ╔═╗ ╔╗╔ ╦ ╦");
        System.out.println("  ║║║ ║╣  ║║║ ║ ║");
        System.out.println("  ╩ ╩ ╚═╝ ╝╚╝ ╚═╝");
    }

    public void end(){
        System.out.println("  ╔═╗ ╦ ╔╗╔");
        System.out.println("  ╠╣  ║ ║║║");
        System.out.println("  ╚   ╩ ╝╚╝");
    }

    /*
     * EMPLOYEES
     */
    public void listEmployees(){
        System.out.println("┬  ┬┌─┐┌┬┐┌─┐  ┌┬┐┌─┐┌─┐  ┌─┐┌─┐┬  ┌─┐┬─┐┬┌─┐┌─┐");
        System.out.println("│  │└─┐ │ ├┤    ││├┤ └─┐  └─┐├─┤│  ├─┤├┬┘│├┤ └─┐");
        System.out.println("┴─┘┴└─┘ ┴ └─┘  ─┴┘└─┘└─┘  └─┘┴ ┴┴─┘┴ ┴┴└─┴└─┘└─┘");
    }
    public void createEmployee(){
        System.out.println("┌─┐┬─┐┌─┐┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┬  ┌─┐┬─┐┬┌─┐");
        System.out.println("│  ├┬┘├┤ ├┤ ├┬┘  │ ││││  └─┐├─┤│  ├─┤├┬┘│├┤ ");
        System.out.println("└─┘┴└─└─┘└─┘┴└─  └─┘┘└┘  └─┘┴ ┴┴─┘┴ ┴┴└─┴└─┘");
    }
    public void updateEmployee(){
        System.out.println("┌┬┐┌─┐┌┬┐┬┌─┐┬┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┬  ┌─┐┬─┐┬┌─┐");
        System.out.println("││││ │ │││├┤ │├┤ ├┬┘  │ ││││  └─┐├─┤│  ├─┤├┬┘│├┤ ");
        System.out.println("┴ ┴└─┘─┴┘┴└  ┴└─┘┴└─  └─┘┘└┘  └─┘┴ ┴┴─┘┴ ┴┴└─┴└─┘");
    }
    public void deleteEmployee(){
        System.out.println("┌─┐┬ ┬┌─┐┌─┐┬─┐┬┌┬┐┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┬  ┌─┐┬─┐┬┌─┐");
        System.out.println("└─┐│ │├─┘├─┘├┬┘││││├┤ ├┬┘  │ ││││  └─┐├─┤│  ├─┤├┬┘│├┤ ");
        System.out.println("└─┘└─┘┴  ┴  ┴└─┴┴ ┴└─┘┴└─  └─┘┘└┘  └─┘┴ ┴┴─┘┴ ┴┴└─┴└─┘");
    }

    /*
    * CONTRACTS
    */
    public void listContracts(){
        System.out.println("┬  ┬┌─┐┌┬┐┌─┐  ┌┬┐┌─┐┌─┐  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┌┬┐┌─┐");
        System.out.println("│  │└─┐ │ ├┤    ││├┤ └─┐  │  │ ││││ │ ├┬┘├─┤ │ └─┐");
        System.out.println("┴─┘┴└─┘ ┴ └─┘  ─┴┘└─┘└─┘  └─┘└─┘┘└┘ ┴ ┴└─┴ ┴ ┴ └─┘");
    }

    public void createContract(){
        System.out.println("┌─┐┬─┐┌─┐┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┌┬┐");
        System.out.println("│  ├┬┘├┤ ├┤ ├┬┘  │ ││││  │  │ ││││ │ ├┬┘├─┤ │ ");
        System.out.println("└─┘┴└─└─┘└─┘┴└─  └─┘┘└┘  └─┘└─┘┘└┘ ┴ ┴└─┴ ┴ ┴ ");
    }

    public void updateContract(){
        System.out.println("┌┬┐┌─┐┌┬┐┬┌─┐┬┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┌┬┐");
        System.out.println("││││ │ │││├┤ │├┤ ├┬┘  │ ││││  │  │ ││││ │ ├┬┘├─┤ │ ");
        System.out.println("┴ ┴└─┘─┴┘┴└  ┴└─┘┴└─  └─┘┘└┘  └─┘└─┘┘└┘ ┴ ┴└─┴ ┴ ┴ ");
    }

    public void deleteContract(){
        System.out.println("┌─┐┬ ┬┌─┐┌─┐┬─┐┬┌┬┐┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┌┬┐");
        System.out.println("└─┐│ │├─┘├─┘├┬┘││││├┤ ├┬┘  │ ││││  │  │ ││││ │ ├┬┘├─┤ │ ");
        System.out.println("└─┘└─┘┴  ┴  ┴└─┴┴ ┴└─┘┴└─  └─┘┘└┘  └─┘└─┘┘└┘ ┴ ┴└─┴ ┴ ┴ ");
    }

    /*
     * COMPANIES
     */
    public void listCompanies(){
        System.out.println("┬  ┬┌─┐┌┬┐┌─┐  ┌┬┐┌─┐┌─┐  ┌─┐┌┐┌┌┬┐┬─┐┌─┐┌─┐┬─┐┬┌─┐┌─┐┌─┐");
        System.out.println("│  │└─┐ │ ├┤    ││├┤ └─┐  ├┤ │││ │ ├┬┘├┤ ├─┘├┬┘│└─┐├┤ └─┐");
        System.out.println("┴─┘┴└─┘ ┴ └─┘  ─┴┘└─┘└─┘  └─┘┘└┘ ┴ ┴└─└─┘┴  ┴└─┴└─┘└─┘└─┘");
    }

    public void createCompany(){
        System.out.println("┌─┐┬─┐┌─┐┌─┐┬─┐  ┬ ┬┌┐┌┌─┐  ┌─┐┌┐┌┌┬┐┬─┐┌─┐┌─┐┬─┐┬┌─┐┌─┐");
        System.out.println("│  ├┬┘├┤ ├┤ ├┬┘  │ ││││├┤   ├┤ │││ │ ├┬┘├┤ ├─┘├┬┘│└─┐├┤ ");
        System.out.println("└─┘┴└─└─┘└─┘┴└─  └─┘┘└┘└─┘  └─┘┘└┘ ┴ ┴└─└─┘┴  ┴└─┴└─┘└─┘");
    }

    public void updateCompany(){
        System.out.println("┌┬┐┌─┐┌┬┐┬┌─┐┬┌─┐┬─┐  ┬ ┬┌┐┌┌─┐  ┌─┐┌┐┌┌┬┐┬─┐┌─┐┌─┐┬─┐┬┌─┐┌─┐");
        System.out.println("││││ │ │││├┤ │├┤ ├┬┘  │ ││││├┤   ├┤ │││ │ ├┬┘├┤ ├─┘├┬┘│└─┐├┤ ");
        System.out.println("┴ ┴└─┘─┴┘┴└  ┴└─┘┴└─  └─┘┘└┘└─┘  └─┘┘└┘ ┴ ┴└─└─┘┴  ┴└─┴└─┘└─┘");
    }

    public void deleteCompany(){
        System.out.println("┌─┐┬ ┬┌─┐┌─┐┬─┐┬┌┬┐┌─┐┬─┐  ┬ ┬┌┐┌┌─┐  ┌─┐┌┐┌┌┬┐┬─┐┌─┐┌─┐┬─┐┬┌─┐┌─┐");
        System.out.println("└─┐│ │├─┘├─┘├┬┘││││├┤ ├┬┘  │ ││││├┤   ├┤ │││ │ ├┬┘├┤ ├─┘├┬┘│└─┐├┤ ");
        System.out.println("└─┘└─┘┴  ┴  ┴└─┴┴ ┴└─┘┴└─  └─┘┘└┘└─┘  └─┘┘└┘ ┴ ┴└─└─┘┴  ┴└─┴└─┘└─┘");
    }

    /*
    * JOBS
    */

    public void listJobs(){
        System.out.println("┬  ┬┌─┐┌┬┐┌─┐  ┌┬┐┌─┐┌─┐  ┌─┐┌─┐┌─┐┌┬┐┌─┐┌─┐");
        System.out.println("│  │└─┐ │ ├┤    ││├┤ └─┐  ├─┘│ │└─┐ │ ├┤ └─┐");
        System.out.println("┴─┘┴└─┘ ┴ └─┘  ─┴┘└─┘└─┘  ┴  └─┘└─┘ ┴ └─┘└─┘");
    }
    public void createJob(){
        System.out.println("┌─┐┬─┐┌─┐┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┌─┐┌┬┐┌─┐");
        System.out.println("│  ├┬┘├┤ ├┤ ├┬┘  │ ││││  ├─┘│ │└─┐ │ ├┤ ");
        System.out.println("└─┘┴└─└─┘└─┘┴└─  └─┘┘└┘  ┴  └─┘└─┘ ┴ └─┘");
    }
    public void updateJob(){
        System.out.println("┌┬┐┌─┐┌┬┐┬┌─┐┬┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┌─┐┌┬┐┌─┐");
        System.out.println("││││ │ │││├┤ │├┤ ├┬┘  │ ││││  ├─┘│ │└─┐ │ ├┤ ");
        System.out.println("┴ ┴└─┘─┴┘┴└  ┴└─┘┴└─  └─┘┘└┘  ┴  └─┘└─┘ ┴ └─┘");
    }    public void deleteJob(){
        System.out.println("┌─┐┬ ┬┌─┐┌─┐┬─┐┬┌┬┐┌─┐┬─┐  ┬ ┬┌┐┌  ┌─┐┌─┐┌─┐┌┬┐┌─┐");
        System.out.println("└─┐│ │├─┘├─┘├┬┘││││├┤ ├┬┘  │ ││││  ├─┘│ │└─┐ │ ├┤ ");
        System.out.println("└─┘└─┘┴  ┴  ┴└─┴┴ ┴└─┘┴└─  └─┘┘└┘  ┴  └─┘└─┘ ┴ └─┘");
    }

}
