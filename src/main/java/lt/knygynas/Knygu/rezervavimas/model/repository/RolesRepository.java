package lt.knygynas.Knygu.rezervavimas.model.repository;

import lt.knygynas.Knygu.rezervavimas.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByPavadinimas(String pavadinimas);
    Roles findById(int id);

}
