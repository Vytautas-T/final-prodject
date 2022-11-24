package lt.knygynas.Knygu.rezervavimas.controller;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import lt.knygynas.Knygu.rezervavimas.model.entity.Kategorijos;
import lt.knygynas.Knygu.rezervavimas.model.repository.AutoriausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AutoriaiMVController {
    @Autowired
    AutoriausRepository autoriausRepository;

    @PostMapping("/aut/admin/istrinti_autoriu")
    String istrinamasAutorius(Model model, @RequestParam int id){
        autoriausRepository.delete(autoriausRepository.findById(id));
        return "autorius_istrintas.html";
    }

    @GetMapping("/aut/admin/redaguoti_autoriu/{id}")
    String redaguojamasAutorius(Model model, @PathVariable int id){
        Autorius autorius = autoriausRepository.findById(id);
        model.addAttribute("autorius", autorius);
        return "redaguoti_autoriu.html";
    }

    @GetMapping("/aut/admin/naujas_autorius")
    String naujasAutorius(Model model) {
        Autorius naujasAutorius = new Autorius();
        model.addAttribute("autorius", naujasAutorius);
        return "ideti_autoriu.html";
    }

    @PostMapping("/aut/idedamas_autorius")
    String idetiAutoriu(Model model, @ModelAttribute Autorius autorius) {
        autorius.setVardas(autorius.getVardas());
        autorius.setPavarde(autorius.getPavarde());
        autorius.setAprasymas(autorius.getAprasymas());
        autoriausRepository.save(autorius);
        return "idetas_autorius.html";
    }

    @GetMapping("/aut/knygos_pagal_autoriu")
    String knygosPagalAutoriu(Model model) {
        List<Autorius> autoriusList = autoriausRepository.findAll();
        model.addAttribute("visiAutoriai", autoriusList);
        return "knygos_pagal_autoriu.html";
    }

    @GetMapping("/aut/visi_autoriai")
    String rodytiVisusAutorius(Model model) {
        List<Autorius> autoriai = autoriausRepository.findAll();
        model.addAttribute("VisiAutoriai", autoriai);
        return "visi_autoriai.html";
    }

    @GetMapping("/aut/rodyti_autoriu")
    String rodytiAutoriausKnygas(Model model, @RequestParam int id) {
        Autorius autorius = autoriausRepository.findById(id);
        model.addAttribute("id", autorius.getId());
        model.addAttribute("vardas", autorius.getVardas());
        model.addAttribute("pavarde", autorius.getPavarde());
        model.addAttribute("aprasymas", autorius.getAprasymas());
        return "parodyti_autoriu.html";
    }

    @GetMapping("/aut/rasti_pagal_autoriu")
    String ieskomaPagalAutorius(Model model, @RequestParam String vardas) {
        Autorius autorius = autoriausRepository.findByVardas(vardas);
        List<Autorius> autoriusList = autoriausRepository.findAll();
        model.addAttribute("autoriausKnygos", autorius.getAutoriausKnygos());
        model.addAttribute("autoriausVardas", autorius.getVardas());
        model.addAttribute("visiAutoriai", autoriusList);
        return "autoriaus_knygos.html";
    }

    @GetMapping("/aut/rasti_autoriu")
    String ieskomasAutorius(Model model, @RequestParam String vardas) {
        Autorius autorius = autoriausRepository.findByVardas(vardas);
        model.addAttribute("id", autorius.getId());
        model.addAttribute("vardas", autorius.getVardas());
        model.addAttribute("pavarde", autorius.getPavarde());
        model.addAttribute("aprasymas", autorius.getAprasymas());
        return "parodyti_autoriu.html";
    }
}
