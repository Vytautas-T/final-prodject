package lt.knygynas.Knygu.rezervavimas.model.repository;

import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VartotojoRepository extends JpaRepository<Vartotojas,Integer> {
    Vartotojas findById(int id);

    Vartotojas findByEmail (String email);
}
