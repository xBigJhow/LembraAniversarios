package com.BigJhow.LembraAniversarios.scheduler;

import com.BigJhow.LembraAniversarios.model.Aniversariante;
import com.BigJhow.LembraAniversarios.service.AniversarianteService;
import com.BigJhow.LembraAniversarios.smtp.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EnviaEmailTask {

    @Autowired
    private AniversarianteService aniversarianteService;
    private final EnviaEmail enviaEmail;

    @Autowired
    public EnviaEmailTask(EnviaEmail enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    @Scheduled(cron = "${spring.scheduled.tasks.enviar-email.cron}")
    public void enviarEmailDiario() {
        System.out.println("CHEGOU NO ENVIAR EMAIL TASK");
        List<Aniversariante> aniversariantes = aniversarianteService.getAniversarianteDoDia();
        enviaEmail.enviarEmail(aniversariantes);
    }
}






