package com.fabricio.OS.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabricio.OS.domain.Tecnico;
import com.fabricio.OS.dtos.TecnicoDTO;
import com.fabricio.OS.repositories.TecnicoRepository;
import com.fabricio.OS.services.exceptions.DataIntegratyViolationException;
import com.fabricio.OS.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);

        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID : " + id + ", Tipo: " + Tecnico.class.getName()));

    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO){
        //verificando se já exisite um usu´ário com o CPF cadastrado
        if(this.findByCpf(tecnicoDTO) != null){
            throw new  DataIntegratyViolationException("CPF já cadastrado no sistema!");
        }
        

        Tecnico tecnico = new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone());
        return tecnicoRepository.save(tecnico);
        
    }

    public Tecnico findByCpf(TecnicoDTO tecnicoDTO){
        Tecnico obj = tecnicoRepository.findByCpf(tecnicoDTO.getCpf());

        if(obj != null){
            return obj;
        }
        return null;
    }

}
