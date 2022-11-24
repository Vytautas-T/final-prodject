package lt.knygynas.Knygu.rezervavimas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kategorijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String pavadinimas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "knygos_kategorijos",
            joinColumns = @JoinColumn(name = "kategorijos_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    Set<Knygos> knygaSuKategorija;
    public Kategorijos() {
    }

    public Kategorijos(int id, String pavadinimas, Set<Knygos> knygaSuKategorija) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.knygaSuKategorija = knygaSuKategorija;
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

    public Set<Knygos> getKnygaSuKategorija() {
        return knygaSuKategorija;
    }

    public void setKnygaSuKategorija(Set<Knygos> knygaSuKategorija) {
        this.knygaSuKategorija = knygaSuKategorija;
    }


    @Override
    public String toString() {
        return "Kategotrijos{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                '}';
    }
}
