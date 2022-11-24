package lt.knygynas.Knygu.rezervavimas.model.repository;

import lt.knygynas.Knygu.rezervavimas.model.entity.Rezervacijos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijosRepository extends JpaRepository<Rezervacijos,Integer > {


}
