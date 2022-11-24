package lt.knygynas.Knygu.rezervavimas.security;

import lt.knygynas.Knygu.rezervavimas.model.entity.Privilegijos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Roles;
import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.PrivilegijosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.RolesRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private VartotojoRepository vartotojoRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PrivilegijosRepository privilegijosrepository;

    @Autowired
    PasswordEncoder passwordEncoder;

   @Override
   public void onApplicationEvent(ContextRefreshedEvent event) {

       if (alreadySetup)
           return;
        Privilegijos skaitytiPrivilegije = sukurtiPrivilegijeJeiguJiNerasta("READ_PRIVILEGE");
        Privilegijos rasytiPrivilegije  = sukurtiPrivilegijeJeiguJiNerasta("WHRITE_PRIVILEGE");
//
      List<Privilegijos> adminPrivilegijos = Arrays.asList(
               skaitytiPrivilegije, rasytiPrivilegije);
       sukurtiRoleJeiguJiNerasta("ROLE_ADMIN", adminPrivilegijos);
      sukurtiRoleJeiguJiNerasta("ROLE_USER", Arrays.asList(skaitytiPrivilegije));

      Roles adminRole = rolesRepository.findByPavadinimas("ROLE_ADMIN");
       Vartotojas vartotojasADMIN = new Vartotojas();
       vartotojasADMIN.setFirstName("Admin");
       vartotojasADMIN.setLastName("admin");
       vartotojasADMIN.setPassword(passwordEncoder.encode("pass"));
       vartotojasADMIN.setEmail("admin");
       vartotojasADMIN.setRoles(Arrays.asList(adminRole));
       vartotojasADMIN.setIjungta(true);
       vartotojoRepository.save(vartotojasADMIN);

       Roles userRole = rolesRepository.findByPavadinimas("ROLE_USER");
       Vartotojas vartotojasUSER = new Vartotojas();
       vartotojasUSER.setFirstName("userName");
       vartotojasUSER.setLastName("userSurname");
       vartotojasUSER.setEmail("user");
       vartotojasUSER.setPassword(passwordEncoder.encode("pass"));
       vartotojasUSER.setRoles(Arrays.asList(userRole));
       vartotojasUSER.setIjungta(true);
       vartotojoRepository.save(vartotojasUSER);

      alreadySetup = true;
   }

    @Transactional
    Privilegijos sukurtiPrivilegijeJeiguJiNerasta(String pavadinimas) {
        Privilegijos privilegijos = privilegijosrepository.findByPavadinimas(pavadinimas);
        if (privilegijos == null){
            privilegijos = new Privilegijos();
            privilegijos.setPavadinimas(pavadinimas);
            privilegijosrepository.save(privilegijos);
        }
        return privilegijos;
    }

    @Transactional
    Roles sukurtiRoleJeiguJiNerasta(String pavadinimas, Collection<Privilegijos> privilegijos){
        Roles role = rolesRepository.findByPavadinimas(pavadinimas);
        if (role == null){
            role = new Roles();
            role.setPavadinimas(pavadinimas);
            role.setPrivelegijos(privilegijos);
            rolesRepository.save(role);
        }
        return role;
    }
}
