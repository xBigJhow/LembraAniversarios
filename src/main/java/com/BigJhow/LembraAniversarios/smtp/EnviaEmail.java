package com.BigJhow.LembraAniversarios.smtp;

import com.BigJhow.LembraAniversarios.model.Aniversariante;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnviaEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${support.mail}")
    private String supportMail;


    public void enviarEmail(List<Aniversariante> aniversariantes) {
        System.out.println("CHEGOU NO ENVIA EMAIL");
        if (aniversariantes.isEmpty()) {
            System.out.println("Nenhum aniversariante hoje.");
            return;
        }
        try{

            MimeMessage mail = javaMailSender.createMimeMessage();

            MimeMessageHelper message = new MimeMessageHelper(mail);

            message.setSubject("Aniversariantes do Dia");
            String mensagem = aniversariantes.stream()
                    .map(pessoa -> String.format("Nome: %s\nData de Nascimento: %s\nObservação: %s\n\n",
                            pessoa.getNome(), pessoa.getData_nascimento(), pessoa.getObs()))
                    .collect(Collectors.joining("\n"));

            message.setText("ANIVERSARIANTES DO DIA:\n\n" + mensagem);
            message.setFrom(supportMail);
            message.setTo("jonatasfreitas14@hotmail.com");
            javaMailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }

    }
}
