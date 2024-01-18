package fr.doandgo.gestionrh.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Adresse extends AbstractBaseEntity {

    @NotNull
    public String street;

    public Integer number;

    @ManyToOne
    @NotNull
    private City city;

}