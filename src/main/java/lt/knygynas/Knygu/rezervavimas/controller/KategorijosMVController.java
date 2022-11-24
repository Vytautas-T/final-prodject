package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import lt.knygynas.Knygu.rezervavimas.model.entity.Kategorijos;
import lt.knygynas.Knygu.rezervavimas.model.entity.Knygos;
import lt.knygynas.Knygu.rezervavimas.model.repository.KategorijosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class KategorijosMVController {
    @Autowired
    KategorijosRepository kategorijosRepository;

    @PostMapping("/kat/admin/istrinti_kategorija/{id}")
    String istrintiKategorija(Model model,@PathVariable int id){
        kategorijosRepository.delete(kategorijosRepository.findById(id));
        return "kategorija_istrinta.html";
    }

    @GetMapping("/kat/admin/nauja_kategorija")
    String naujaKategorija(Model model){
        return "ideti_kategorija.html";
    }

    @PostMapping("/kat/idedama_kategorija")
    String idetiKategorija(String pavadinimas){
        Kategorijos naujaKategorija = new Kategorijos();
        naujaKategorija.setPavadinimas(pavadinimas);
        kategorijosRepository.save(naujaKategorija);
        return "ideta_kategorija.html";
    }

    @GetMapping("/kat/kat_skiltis")
    String kategorijosSkiltis(Model model){
        return "visos_kategorijos.html";
    }

    @GetMapping("/kat/visos_kategorijos")
    String rodytiVisasKategorijas(Model model){
        List<Kategorijos> visosKategorijos = kategorijosRepository.findAll();
        model.addAttribute("visosKategorijos", visosKategorijos);
        if (visosKategorijos.size() == 0){
            model.addAttribute("tekstas" , "sios kategorijos nera");
            return "visos_kategorijos.html";
        }
        return "visos_kategorijos.html";
    }

    @GetMapping("/kat/rodyti_kategorijas")
    String rodytiKategorijjosknygos(Model model,@RequestParam int id){
        Kategorijos kategorija = kategorijosRepository.findById(id);
        model.addAttribute("id", kategorija.getId());
        model.addAttribute("pavadinimas", kategorija.getPavadinimas());
        model.addAttribute("kategorijosKnygos" , kategorija.getKnygaSuKategorija());
        model.addAttribute("kategorijosPavadinimas" , kategorija.getPavadinimas());
        return "kategorijos_knygos.html";
    }

    @GetMapping("/kat/rasti_pagal_kategorija")
    String ieskomaKategorija(Model model, @RequestParam String pavadinimas){
        Kategorijos kategorija = kategorijosRepository.findByPavadinimas(pavadinimas);
        model.addAttribute("kategorijosKnygos" ,kategorija.getKnygaSuKategorija() );
        model.addAttribute("kategorijosPavadinimas", kategorija.getPavadinimas());
        return "kategorijos_knygos.html";
    }

    @GetMapping("/kat/admin/redaguoti_kategorija/{id}")
    String redaguojamaKategorija(Model model,@PathVariable int id){
        Kategorijos kategorija = kategorijosRepository.findById(id);
        model.addAttribute("kategorija" , kategorija);
        return "redaguoti_kategorija.html";
    }

    @PostMapping("/kat/isaugojema_kategorija")
    String idedamaKnyga(Model model, @ModelAttribute Kategorijos kategorija){
        System.out.println(kategorija);
        kategorija.setPavadinimas(kategorija.getPavadinimas());
        kategorijosRepository.save(kategorija);
        return "ideta_kategorija.html";
    }

}
