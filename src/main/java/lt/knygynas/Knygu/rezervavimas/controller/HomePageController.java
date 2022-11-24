package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HomePageController {
    @Autowired
    VartotojoRepository vartotojoRepository;


    @GetMapping("/")
    String namuPuslapis(Model model, Principal principal){
        principal.getName();
        Vartotojas vartotojoEmail = vartotojoRepository.findByEmail(principal.getName());
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        System.out.println(principal.getName());
        System.out.println(vartotojoEmail.getLastName());
        return "homePage.html";
    }

}
