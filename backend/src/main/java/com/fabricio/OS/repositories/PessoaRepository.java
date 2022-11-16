package com.fabricio.OS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fabricio.OS.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
    
        //Query personalizada
        @Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
        Pessoa findByCpf(@Param("cpf") String cpf);
    
}
