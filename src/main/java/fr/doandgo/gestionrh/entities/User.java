package fr.doandgo.gestionrh.entities;

import fr.doandgo.gestionrh.enums.AppRole;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractBaseEntity{
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private Date birthDate;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private AppRole role;

    public User(Integer id, String firstname, String lastname, Date birthDate, String email, String password, AppRole role) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
