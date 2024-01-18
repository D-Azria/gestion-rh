package fr.doandgo.gestionrh.entities;


import jakarta.persistence.Entity;
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
public class Company extends AbstractBaseEntity{

    @NotNull
    private String name;

    private String url;

    @OneToMany
    private List<Job> jobs;

    public Company(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Company(Integer id, String name, String url) {
        super(id);
        this.name = name;
        this.url = url;
    }
}