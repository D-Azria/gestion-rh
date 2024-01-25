package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "job")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Job> jobsManaged;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Job manager;

    public void addManagedJob(Job managedJob) {
        if (jobsManaged == null) {
            jobsManaged = new ArrayList<>();
        }
        jobsManaged.add(managedJob);
        managedJob.setManager(this);
    }

    public void removeManagedJob(Job managedJob) {
        if (jobsManaged != null) {
            jobsManaged.remove(managedJob);
            managedJob.setManager(null);
        }
    }

}
