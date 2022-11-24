package lt.knygynas.Knygu.rezervavimas.model.repository;

import lt.knygynas.Knygu.rezervavimas.model.entity.Kategorijos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorijosRepository extends JpaRepository<Kategorijos,Integer> {

    Kategorijos findByPavadinimas(String pavadinimas);
    Kategorijos findById(int id);
}
