package com.fabricio.OS.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabricio.OS.domain.Cliente;
import com.fabricio.OS.domain.OS;
import com.fabricio.OS.domain.Tecnico;
import com.fabricio.OS.domain.enums.Prioridade;
import com.fabricio.OS.domain.enums.Status;
import com.fabricio.OS.dtos.OSDTO;
import com.fabricio.OS.repositories.OSRepository;
import com.fabricio.OS.services.exceptions.ObjectNotFoundException;

@Service
public class OSService {
    
    @Autowired
    private OSRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id){
        Optional<OS> obj = repository.findById(id);

        return obj.orElseThrow(()->new ObjectNotFoundException("Ordem de serviço não encontrada. Id = " + id + " Tipo = " + OS.class.getName()));
    }

    public List<OS> findAll(){
        return repository.findAll();
    }

    public OS create(@Valid OSDTO obj) {
        //método que converta a DTO em um objeto OS
        OS objAserSalvo = fromDTO(obj);

        //retornando o objeto salvo lá para o controller
        return objAserSalvo;
    }


    public OS update(@Valid OSDTO obj) {
        findById(obj.getId());

        return fromDTO(obj);
    }

    private OS fromDTO (OSDTO objDTO){
        OS newObj = new OS();
        newObj.setId(objDTO.getId());
        newObj.setObservacoes(objDTO.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        newObj.setStatus(Status.toEnum(objDTO.getStatus()));

        if(newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }

        Tecnico tec = tecnicoService.findById(objDTO.getTecnico()); 
        Cliente cli = clienteService.findById(objDTO.getCliente());
        
        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        return repository.save(newObj);
    }

}
