package lt.knygynas.Knygu.rezervavimas.model.repository;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoriausRepository extends JpaRepository<Autorius, Integer> {
    Autorius findByVardas(String vardas);
    Autorius findById(int id);
}
