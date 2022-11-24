package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Knygos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Rezervacijos;
import lt.knygynas.Knygu.rezervavimas.model.repository.KnygosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.RezervacijosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RezervacijosController {
    @Autowired
    KnygosRepository knygosRepository;
    @Autowired
    RezervacijosRepository rezervacijosRepository;
    @Autowired
    VartotojoRepository vartotojoRepository;

    @PostMapping("/rezervacijos/rezervuoti_knyga/{knygosId}")
    String uzrezervuota(Model model, @RequestParam int rezervuojamasKiekis, @PathVariable int knygosId){
        Rezervacijos rezervuotas = new Rezervacijos();
        Knygos knygos = knygosRepository.findById(knygosId);
        rezervuotas.setKiekis(rezervuojamasKiekis);
        rezervuotas.setKurisUzRezervavo(vartotojoRepository.findById(1));
        rezervuotas.setUzrezervuotaKnyga(knygos);
        knygos.kiekioApskaiciavimas(rezervuojamasKiekis);
        rezervacijosRepository.save(rezervuotas);
        return "uzrezervuota_knyga.html";
    }
}
