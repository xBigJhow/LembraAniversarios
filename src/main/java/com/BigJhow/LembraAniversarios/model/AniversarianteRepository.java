package com.BigJhow.LembraAniversarios.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AniversarianteRepository extends JpaRepository<Aniversariante, Long> {

    @Query("SELECT a FROM Aniversariante a WHERE DAY(a.data_nascimento) = DAY(:dataAtual) AND MONTH(a.data_nascimento) = MONTH(:dataAtual)")
    List<Aniversariante> findByDataNascimento(@Param("dataAtual") Date dataAtual);
}