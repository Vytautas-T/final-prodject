package lt.knygynas.Knygu.rezervavimas.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilegijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    @ManyToMany(mappedBy = "privelegijos")
    private Collection<Roles> roles;

    public Privilegijos() {
    }

    public Privilegijos(int id, String pavadinimas, Collection<Roles> roles) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Privelegijos{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", roles=" + roles +
                '}';
    }
}
