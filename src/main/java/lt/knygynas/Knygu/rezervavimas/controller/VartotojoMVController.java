package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Roles;
import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.KnygosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.RolesRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;

@Controller
public class VartotojoMVController {
    @Autowired
    VartotojoRepository vartotojoRepository;
    @Autowired
    KnygosRepository knygosRepository;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/vartotojas/pamegti_knyga/{id}")
    String pamegtiknyga(@PathVariable int id, Principal principal){
        Vartotojas vartotojas = vartotojoRepository.findByEmail(principal.getName());
        vartotojas.getMegstamosKnygos().add(knygosRepository.findById(id));
        vartotojoRepository.save(vartotojas);
        return "pamegtos_knygos.html";
    }

    @GetMapping("/vartotojas/admin/registracije")
    String naujasVartotojas(Model model){
        Vartotojas vartotojas = new Vartotojas();
        Collection<Roles> role = rolesRepository.findAll();
        model.addAttribute("vartotojas", vartotojas);
        model.addAttribute("roles",role);
        return "vartotojo_registracije.html";
    }

    @PostMapping("/vartotojas/idetas")
    String idedamasVartotojas(Vartotojas vartotojas, @RequestParam int roles){
        vartotojas.setIjungta(true);
        vartotojas.setTokenExpired(false);
        vartotojas.setPassword(passwordEncoder.encode(vartotojas.getPassword()));
        vartotojoRepository.save(vartotojas);
        return "vartotojas_idetas.html";
    }
}
