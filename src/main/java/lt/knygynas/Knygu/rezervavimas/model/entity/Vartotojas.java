package lt.knygynas.Knygu.rezervavimas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Vartotojas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;
    @Column(unique = true)
    private String email;

    private String password;

    private boolean ijungta;

    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "vartotojas_roles",
            joinColumns = @JoinColumn(
                    name = "vartotojo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_id", referencedColumnName = "id"))
    private Collection<Roles> roles;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "vartotojas_knygos",
            joinColumns = @JoinColumn(name = "vartotojas_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    Set<Knygos> vartotojoKnygos;

    @JsonIgnore
    @OneToMany(mappedBy = "kurisUzRezervavo")
    Set<Rezervacijos> vartotojoRezervacijos;

    @ManyToMany
    @JoinTable(
            name = "vartotoju_megstamos_knygos",
            joinColumns = @JoinColumn(name = "vartotojo_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    private Set<Knygos> megstamosKnygos;

    public Vartotojas() {
    }

    public Vartotojas(int id, String firstName, String lastName, String email, String password, boolean ijungta, boolean tokenExpired, Collection<Roles> roles, Set<Knygos> vartotojoKnygos, Set<Rezervacijos> vartotojoRezervacijos, Set<Knygos> megstamosKnygos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.ijungta = ijungta;
        this.tokenExpired = tokenExpired;
        this.roles = roles;
        this.vartotojoKnygos = vartotojoKnygos;
        this.vartotojoRezervacijos = vartotojoRezervacijos;
        this.megstamosKnygos = megstamosKnygos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIjungta() {
        return ijungta;
    }

    public void setIjungta(boolean ijungta) {
        this.ijungta = ijungta;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }

    public Set<Knygos> getVartotojoKnygos() {
        return vartotojoKnygos;
    }

    public void setVartotojoKnygos(Set<Knygos> vartotojoKnygos) {
        this.vartotojoKnygos = vartotojoKnygos;
    }

    public Set<Rezervacijos> getVartotojoRezervacijos() {
        return vartotojoRezervacijos;
    }

    public void setVartotojoRezervacijos(Set<Rezervacijos> vartotojoRezervacijos) {
        this.vartotojoRezervacijos = vartotojoRezervacijos;
    }

    public Set<Knygos> getMegstamosKnygos() {
        return megstamosKnygos;
    }

    public void setMegstamosKnygos(Set<Knygos> megstamosKnygos) {
        this.megstamosKnygos = megstamosKnygos;
    }

    @Override
    public String toString() {
        return "Vartotojas{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ijungta=" + ijungta +
                ", tokenExpired=" + tokenExpired +
                ", roles=" + roles +
                ", vartotojoKnygos=" + vartotojoKnygos +
                ", vartotojoRezervacijos=" + vartotojoRezervacijos +
                ", megstamosKnygos=" + megstamosKnygos +
                '}';
    }
}
