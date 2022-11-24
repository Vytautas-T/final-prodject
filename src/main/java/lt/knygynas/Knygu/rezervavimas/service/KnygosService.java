package lt.knygynas.Knygu.rezervavimas.service;

import lt.knygynas.Knygu.rezervavimas.model.entity.Autorius;
import lt.knygynas.Knygu.rezervavimas.model.repository.AutoriausRepository;
import lt.knygynas.Knygu.rezervavimas.model.repository.KnygosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KnygosService {

    @Autowired
    AutoriausRepository autoriausRepository;
    @Autowired
    KnygosRepository knygosRepository;

    public Set<Autorius> konvertavimasIsStringISet(String autoriai){
        Set<Autorius> autSet = new HashSet<>();
        for (String autorius : autoriai.split(",")){
            autSet.add(autoriausRepository.findByVardas(autorius));
        }
        return autSet;
    }

    public String konvertavimasIsSetIString(Set<Autorius> autoriai){
        StringBuilder autoString = new StringBuilder();
        for (Autorius autorius: autoriai){
            autoString.append(autorius.getVardas()).append(",");
        }
        return autoString.toString();
    }
}
