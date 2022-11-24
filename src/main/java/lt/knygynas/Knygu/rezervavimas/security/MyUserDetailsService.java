package lt.knygynas.Knygu.rezervavimas.security;

import lt.knygynas.Knygu.rezervavimas.model.entity.Privilegijos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Roles;
import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.RolesRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    VartotojoRepository vartotojoRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Vartotojas vartotojas = vartotojoRepository.findByEmail(email);
        if (vartotojas == null) {
            return new org.springframework.security.core.userdetails.User(" ", " ", true,true,true,true, getAuthorities(Arrays.asList(rolesRepository.findByPavadinimas("ROLE_USER"))));
        }
        return new org.springframework.security.core.userdetails.User(vartotojas.getEmail(),vartotojas.getPassword(),vartotojas.isIjungta(), true, true,true, getAuthorities(vartotojas.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles> roles) {
        return getGrantedAuthorities(getPriviliges(roles));
    }

    private List<String> getPriviliges(Collection<Roles> roles) {
        List<String> priviliges = new ArrayList<>();
        List<Privilegijos> collection = new ArrayList<>();
        for (Roles role : roles)  {
            priviliges.add(role.getPavadinimas());
            collection.addAll(role.getPrivelegijos());
        }
        for (Privilegijos item : collection){
            priviliges.add(item.getPavadinimas());
        }
        return priviliges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> priviliges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String pivilege : priviliges) {
            authorities.add( new SimpleGrantedAuthority(pivilege));
        }
        return authorities;
    }



}
