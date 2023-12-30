package com.BigJhow.LembraAniversarios.service;

import com.BigJhow.LembraAniversarios.model.AniversarianteRepository;
import com.BigJhow.LembraAniversarios.model.Aniversariante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AniversarianteService {

    @Autowired
    private AniversarianteRepository aniversarianteRepository;

    public List<Aniversariante> list(){
        return aniversarianteRepository.findAll();
    }

   public Optional<Aniversariante> findById(final Long id) {
        return aniversarianteRepository.findById(id);
    }

    public Aniversariante update(Aniversariante aniversariante) {
        if (aniversariante.getId() != null && aniversarianteRepository.existsById(aniversariante.getId())) {
            return aniversarianteRepository.save(aniversariante);
        } else {
            return null;
        }
   }

   public void remove(final Long id){

        aniversarianteRepository.deleteById(id);
   }

   public void create(final Aniversariante aniversariante){
        aniversarianteRepository.save(aniversariante);
   }

   public List<Aniversariante> getAniversarianteDoDia(){
       Calendar calendar = Calendar.getInstance();
       Date dataAtual = calendar.getTime();
        return aniversarianteRepository.findByDataNascimento(dataAtual);
   }

}
