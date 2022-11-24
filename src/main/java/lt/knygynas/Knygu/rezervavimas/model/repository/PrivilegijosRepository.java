package lt.knygynas.Knygu.rezervavimas.model.repository;

import lt.knygynas.Knygu.rezervavimas.model.entity.Privilegijos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegijosRepository extends JpaRepository<Privilegijos, Integer> {
    Privilegijos findByPavadinimas(String pavadinimas);
    Privilegijos findById(int id);
}
