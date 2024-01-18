package fr.doandgo.gestionrh.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Amendments extends AbstractBaseEntity {

    @NotNull
    public Date date;

    public String urlDoc;

    @ManyToOne
    @NotNull
    private Contract contract;

}