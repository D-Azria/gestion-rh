package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.Diplomas;
import jakarta.persistence.*;
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
public class Employee extends AbstractBaseEntity{

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private Date birthDate;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Diplomas> diplomas;

    @OneToMany
    private List<Contract> contracts;

    public Employee(String firstname, String lastname, Date birthDate, List<Diplomas> diplomas) {
    }

    /*
    @ManyToOne
    @NotNull
    private Adresse adresse;
    */

}
