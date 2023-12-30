package com.BigJhow.LembraAniversarios.controller;
import com.BigJhow.LembraAniversarios.model.Aniversariante;
import com.BigJhow.LembraAniversarios.service.AniversarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/aniversariante")
public class AniversarianteController {

    @Autowired
    private AniversarianteService aniversarianteService;
    @GetMapping("/listar")
    public String getAllAniversariantesPage(final Model model){

        List<Aniversariante> aniversarianteList = aniversarianteService.list();
        model.addAttribute("aniversarianteList", aniversarianteList);
        return "/aniversariante/list";
    }

    @GetMapping("/detalhes/{id}")
    public String getAniversariantePage(@PathVariable("id") final Long id, final Model model){

        Optional<Aniversariante> aniversariante = aniversarianteService.findById(id);
        model.addAttribute("aniversariante", aniversariante);
        return "/aniversariante/detail";
    }

    @GetMapping("/editar/{id}")
    public String getEditPage(@PathVariable("id") final Long id, final Model model){
        Optional<Aniversariante> aniversariante = aniversarianteService.findById(id);
        model.addAttribute("aniversariante", aniversariante);
        return "aniversariante/edit";

    }

    @PostMapping("/atualizar")
    public String updateAniversariante(final Aniversariante aniversariante){

        Aniversariante updateAniversariante = aniversarianteService.update(aniversariante);

        return "redirect:/aniversariante/listar";
    }

    @GetMapping("/remover/{id}")
    public String removeAniversariante(@PathVariable("id") final Long id){
        aniversarianteService.remove(id);
        return "redirect:/aniversariante/listar";
    }

    @GetMapping("/criar")
    public String getAniversariantePage(final Aniversariante aniversariante){

        return "/aniversariante/create";
    }

    @PostMapping("/registrar")
    public String createAniversariante(final Aniversariante aniversariante){
        aniversarianteService.create(aniversariante);
        return "redirect:/aniversariante/listar";
    }

}