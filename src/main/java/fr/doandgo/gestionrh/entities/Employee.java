package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.Diplomas;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractBaseEntity{

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private Date birthDate;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Diplomas> diplomas;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    public Employee(String firstname, String lastname, Date birthDate, List<Diplomas> diplomas) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.diplomas = diplomas;
        this.contracts = new ArrayList<>();
    }

    public Employee(Integer id, String firstname, String lastname, Date birthDate, List<Diplomas> diplomas, List<Contract> contracts) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.diplomas = diplomas;
        this.contracts = contracts;
    }

    /*
    @ManyToOne
    @NotNull
    private Adresse adresse;
    */

}
