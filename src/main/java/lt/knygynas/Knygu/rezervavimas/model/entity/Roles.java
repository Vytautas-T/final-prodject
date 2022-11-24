package lt.knygynas.Knygu.rezervavimas.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    @ManyToMany(mappedBy = "roles")
    private Collection<Vartotojas> vartotojas;

    @ManyToMany
    @JoinTable(
            name = "roles_privilegijos",
            joinColumns = @JoinColumn(
                    name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilegijos_id", referencedColumnName = "id"))
    private Collection<Privilegijos> privelegijos;

    public Roles() {
    }

    public Roles(int id, String pavadinimas, Collection<Vartotojas> vartotojas, Collection<Privilegijos> privelegijos) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.vartotojas = vartotojas;
        this.privelegijos = privelegijos;
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

    public Collection<Vartotojas> getVartotojas() {
        return vartotojas;
    }

    public void setVartotojas(Collection<Vartotojas> vartotojas) {
        this.vartotojas = vartotojas;
    }

    public Collection<Privilegijos> getPrivelegijos() {
        return privelegijos;
    }

    public void setPrivelegijos(Collection<Privilegijos> privelegijos) {
        this.privelegijos = privelegijos;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", vartotojas=" + vartotojas +
                ", privelegijos=" + privelegijos +
                '}';
    }
}
