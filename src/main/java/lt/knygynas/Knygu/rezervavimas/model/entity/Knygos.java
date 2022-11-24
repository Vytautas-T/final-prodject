package lt.knygynas.Knygu.rezervavimas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Knygos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    private int puslapiuSkait;
    @Column(length = 800)
    private String aprasymas;

    private String turinys;

    private int kiekis;

    private int rezervoutasKiekis;


    @JsonIgnore
    @OneToMany(mappedBy = "uzrezervuotaKnyga")
    Set<Rezervacijos> knygosRezervacijos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "vartotojas_knygos",
            joinColumns = @JoinColumn(name = "knygos_id"),
            inverseJoinColumns = @JoinColumn(name = "vartotojas_id")
    )
    Set<Vartotojas> knygosVartotojei;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "autorius_knygos",
            joinColumns = @JoinColumn(name = "knygos_id"),
            inverseJoinColumns = @JoinColumn(name = "autoriaus_id")
    )
    Set<Autorius> knygosAutoriai;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "knygos_kategorijos",
            joinColumns = @JoinColumn(name = "knygos_id"),
            inverseJoinColumns = @JoinColumn(name = "kategorijos_id")
    )
    Set<Kategorijos> knygosKategorijos;

    @ManyToMany
    @JoinTable(
            name = "vartotoju_megstamos_knygos",
            joinColumns = @JoinColumn(name = "knygos_id"),
            inverseJoinColumns = @JoinColumn(name = "vartotojo_id")
    )
    private Set<Vartotojas> vartotojoPamegtaKnyga;

    public boolean kiekioApskaiciavimas(int rezervuojamasKiekis){
        if (kiekis <= rezervuojamasKiekis){
            return false;
        }
        kiekis = kiekis - rezervuojamasKiekis;
        rezervoutasKiekis += rezervuojamasKiekis;

        return true;
    }

    public Knygos() {
    }

    public Knygos(int id, String pavadinimas, int puslapiuSkait, String aprasymas, String turinys, int kiekis, int rezervoutasKiekis, Set<Vartotojas> knygosVartotojei, Set<Autorius> knygosAutoriai, Set<Kategorijos> knygosKategorijos, Set<Vartotojas> vartotojoPamegtaKnyga) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.puslapiuSkait = puslapiuSkait;
        this.aprasymas = aprasymas;
        this.turinys = turinys;
        this.kiekis = kiekis;
        this.rezervoutasKiekis = rezervoutasKiekis;
        this.knygosVartotojei = knygosVartotojei;
        this.knygosAutoriai = knygosAutoriai;
        this.knygosKategorijos = knygosKategorijos;
        this.vartotojoPamegtaKnyga = vartotojoPamegtaKnyga;
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

    public int getPuslapiuSkait() {
        return puslapiuSkait;
    }

    public void setPuslapiuSkait(int puslapiuSkait) {
        this.puslapiuSkait = puslapiuSkait;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public String getTurinys() {
        return turinys;
    }

    public void setTurinys(String turinys) {
        this.turinys = turinys;
    }

    public int getKiekis() {
        return kiekis;
    }

    public void setKiekis(int kiekis) {
        this.kiekis = kiekis;
    }

    public Set<Vartotojas> getKnygosVartotojei() {
        return knygosVartotojei;
    }

    public void setKnygosVartotojei(Set<Vartotojas> knygosVartotojei) {
        this.knygosVartotojei = knygosVartotojei;
    }

    public Set<Autorius> getKnygosAutoriai() {
        return knygosAutoriai;
    }

    public void setKnygosAutoriai(Set<Autorius> knygosAutoriai) {
        this.knygosAutoriai = knygosAutoriai;
    }

    public Set<Kategorijos> getKnygosKategorijos() {
        return knygosKategorijos;
    }

    public void setKnygosKategorijos(Set<Kategorijos> knygosKategorijos) {
        this.knygosKategorijos = knygosKategorijos;
    }

    public Set<Vartotojas> getVartotojoPamegtaKnyga() {
        return vartotojoPamegtaKnyga;
    }

    public void setVartotojoPamegtaKnyga(Set<Vartotojas> vartotojoPamegtaKnyga) {
        this.vartotojoPamegtaKnyga = vartotojoPamegtaKnyga;
    }

    public int getRezervoutasKiekis() {
        return rezervoutasKiekis;
    }

    public void setRezervoutasKiekis(int rezervoutasKiekis) {
        this.rezervoutasKiekis = rezervoutasKiekis;
    }

    @Override
    public String toString() {
        return "Knygos{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", puslapiuSkait=" + puslapiuSkait +
                ", aprasymas='" + aprasymas + '\'' +
                ", turinys='" + turinys + '\'' +
                ", kiekis=" + kiekis +
                ", rezervoutasKiekis=" + rezervoutasKiekis +
                ", knygosVartotojei=" + knygosVartotojei +
                ", knygosAutoriai=" + knygosAutoriai +
                ", knygosKategorijos=" + knygosKategorijos +
                ", vartotojoPamegtaKnyga=" + vartotojoPamegtaKnyga +
                '}';
    }
}
