package lt.knygynas.Knygu.rezervavimas.model.entity;

import javax.persistence.*;

@Entity
public class Rezervacijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int kiekis;

    @ManyToOne
    @JoinColumn(name = "vartotojas_id")
    private Vartotojas kurisUzRezervavo;

    @ManyToOne
    @JoinColumn(name = "kuri_knyga_uzrezervuota")
    private Knygos uzrezervuotaKnyga;

    public Rezervacijos() {
    }

    public Rezervacijos(int id, int kiekis, Vartotojas kurisUzRezervavo, Knygos uzrezervuotaKnyga) {
        this.id = id;
        this.kiekis = kiekis;
        this.kurisUzRezervavo = kurisUzRezervavo;
        this.uzrezervuotaKnyga = uzrezervuotaKnyga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKiekis() {
        return kiekis;
    }

    public void setKiekis(int kiekis) {
        this.kiekis = kiekis;
    }

    public Vartotojas getKurisUzRezervavo() {
        return kurisUzRezervavo;
    }

    public void setKurisUzRezervavo(Vartotojas kurisUzRezervavo) {
        this.kurisUzRezervavo = kurisUzRezervavo;
    }

    public Knygos getUzrezervuotaKnyga() {
        return uzrezervuotaKnyga;
    }

    public void setUzrezervuotaKnyga(Knygos uzrezervuotaKnyga) {
        this.uzrezervuotaKnyga = uzrezervuotaKnyga;
    }

    @Override
    public String toString() {
        return "Rezervacijos{" +
                "id=" + id +
                ", kiekis=" + kiekis +
                ", kurisUzRezervavo=" + kurisUzRezervavo +
                ", uzrezervuotaKnyga=" + uzrezervuotaKnyga +
                '}';
    }
}
