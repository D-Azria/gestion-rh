package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Job extends AbstractBaseEntity {

    @NotNull
    private String name;

    @NotNull
    private Service service;

    @NotNull
    private Category category;

    @ManyToOne
    @NotNull
    private Company company;

    @OneToMany
    private List<Contract> contracts;

    @OneToMany
    private List<Job> supervisedJobs;

}
