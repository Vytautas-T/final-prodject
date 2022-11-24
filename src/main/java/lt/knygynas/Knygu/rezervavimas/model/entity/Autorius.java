package lt.knygynas.Knygu.rezervavimas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Autorius {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vardas;

    private String pavarde;

    private String aprasymas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "autorius_knygos",
            joinColumns = @JoinColumn(name = "autoriaus_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    Set<Knygos> autoriausKnygos;

    public Autorius() {
    }

    public Autorius(int id, String vardas, String pavarde, String aprasymas, Set<Knygos> autoriausKnygos) {
        this.id = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.aprasymas = aprasymas;
        this.autoriausKnygos = autoriausKnygos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public Set<Knygos> getAutoriausKnygos() {
        return autoriausKnygos;
    }

    public void setAutoriausKnygos(Set<Knygos> autoriausKnygos) {
        this.autoriausKnygos = autoriausKnygos;
    }

    @Override
    public String toString() {
        return "Autorius{" +
                "id=" + id +
                ", vardas='" + vardas + '\'' +
                ", pavarde='" + pavarde + '\'' +
                ", aprasymas='" + aprasymas + '\'' +
                ", autoriausKnygos=" + autoriausKnygos +
                '}';
    }
}
