package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import lt.knygynas.Knygu.rezervavimas.model.entity.Knygos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Vartotojas;
import lt.knygynas.Knygu.rezervavimas.model.repository.AutoriausRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.KategorijosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.KnygosRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.VartotojoRepository;
import lt.knygynas.Knygu.rezervavimas.service.KnygosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class KnygosMVController {
    @Autowired
    KnygosRepository knygosRepository;
    @Autowired
    VartotojoRepository vartotojoRepository;
    @Autowired
    AutoriausRepository autoriausRepository;
    @Autowired
    KategorijosRepository kategorijosRepository;
    @Autowired
    KnygosService knygosService;

    @GetMapping("/knyg/paieska")
    String ieskomaKnyga(Model model){
        return "rasti_knyga.html";
    }

    @GetMapping("/knyg/visos_knygos")
    String rodytiVisasKnygas(Model model) {
        List<Knygos> visosKnygos = knygosRepository.findAll();
        model.addAttribute("visosKnygos", visosKnygos);
        return "rodyti_knygas.html";
    }
    @GetMapping("/knyg/admin/knygos_idejimas")
    String knygosIdejimas(Model model){
        Knygos naujaKnyga = new Knygos();
        model.addAttribute("knyga", naujaKnyga);
        model.addAttribute("kategorijos", kategorijosRepository.findAll());
        return "ideti_knyga.html";
    }
    @PostMapping("/knyg/ideta_knyga")
    String idedamaKnyga(Model model, @ModelAttribute Knygos knyga, @RequestParam String autorius){
        Set<Autorius> autoriusVardas = knygosService.konvertavimasIsStringISet(autorius);
        knyga.setKnygosAutoriai(autoriusVardas);
        knygosRepository.save(knyga);
        return "ideta_knyga.html";
    }

    @GetMapping("/knyg/rasti_knyga_id")
    String ieskomaKnygaPagalId(Model model,@RequestParam int id){
        Knygos knyga = knygosRepository.findById(id);
        model.addAttribute("id" , knyga.getId());
        model.addAttribute("pavadinimas", knyga.getPavadinimas());
        model.addAttribute("puslapiuSkait", knyga.getPuslapiuSkait());
        model.addAttribute("aprasymas", knyga.getAprasymas());
        model.addAttribute("turinys", knyga.getTurinys());
        model.addAttribute("kiekis", knyga.getKiekis());
        model.addAttribute("knygosVartotojei", knyga.getKnygosVartotojei());
        model.addAttribute("KnygosAutoriai", knyga.getKnygosAutoriai());
        model.addAttribute("knygosKategorijos", knyga.getKnygosKategorijos());
        return "parodyti_knyga.html";
    }

    @GetMapping("/knyg/rasti_knyga")
    String ieskomaKnyga(Model model , @RequestParam String pavadinimas){
        System.out.println("bla");
        Knygos knyga = knygosRepository.findByPavadinimas(pavadinimas);
        System.out.println(knyga);
        System.out.println(knyga.getId());
        if(knyga == null){
            System.out.println("cia veikia");
            model.addAttribute("tekstas", "tokios knygos nera");
            return "rasti_knygas.html";
        }
        model.addAttribute("id", knyga.getId());
        model.addAttribute("pavadinimas", knyga.getPavadinimas());
        model.addAttribute("puslapiuSkait", knyga.getPuslapiuSkait());
        model.addAttribute("aprasymas", knyga.getAprasymas());
        model.addAttribute("turinys" , knyga.getTurinys());
        model.addAttribute("kiekis", knyga.getKiekis());
        model.addAttribute("knygosVartotojei", knyga.getKnygosVartotojei());
        model.addAttribute("KnygosAutoriai", knyga.getKnygosAutoriai());
        model.addAttribute("knygosKategorijos", knyga.getKnygosKategorijos());
        return "parodyti_knyga.html";
    }

    @GetMapping("/knyg/rasti_knygas2")
    String ieskomosKnygos(Model model , @RequestParam String pavadinimas){
        Collection<Knygos> rastosKnygos = knygosRepository.findAllByPavadinimas(pavadinimas);
        System.out.println("rastu knyguskaicius yra " + rastosKnygos.size());
        if (rastosKnygos.size() > 1){
            model.addAttribute("visosKnygos", rastosKnygos);
            return "rastos_kelios_knygos.html";
        }
        if (rastosKnygos.size() == 0){
            model.addAttribute("visosKnygos", knygosRepository.findAll());
            model.addAttribute("tekstas" ,"tokios knygos nera");
            return "rodyti_knygas.html";
        }
        Knygos knyga = new Knygos();
        Iterator it = rastosKnygos.iterator();
        while (it.hasNext()) {
            knyga = (Knygos) it.next();
        }
        model.addAttribute("id", knyga.getId());
        model.addAttribute("pavadinimas", knyga.getPavadinimas());
        model.addAttribute("puslapiuSkait", knyga.getPuslapiuSkait());
        model.addAttribute("aprasymas", knyga.getAprasymas());
        model.addAttribute("turinys" , knyga.getTurinys());
        model.addAttribute("kiekis", knyga.getKiekis());
        model.addAttribute("knygosVartotojei", knyga.getKnygosVartotojei());
        model.addAttribute("KnygosAutoriai", knyga.getKnygosAutoriai());
        model.addAttribute("knygosKategorijos", knyga.getKnygosKategorijos());
        return "parodyti_knyga.html";
    }


    @PostMapping("/knyg/admin/istrinti_knyga/{id}")
    String istrintiKyga(Model model, @PathVariable int id) {
        knygosRepository.delete(knygosRepository.findById(id));
        return "istrinta_knyga.html";
    }

    @GetMapping("/knyg/admin/redaguoti_knyga/{id}")
    String redaguojamaKnyga(Model model, @PathVariable  int id){
      Knygos knyga = knygosRepository.findById(id);
      model.addAttribute("knyga", knyga);
      model.addAttribute("kategorijos", kategorijosRepository.findAll());
      model.addAttribute("autorius", knygosService.konvertavimasIsSetIString(knyga.getKnygosAutoriai()));
        return "redaguoti_knyga.html";
    }
}
