package com.fabricio.OS.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabricio.OS.domain.Pessoa;
import com.fabricio.OS.domain.Tecnico;
import com.fabricio.OS.dtos.TecnicoDTO;
import com.fabricio.OS.repositories.PessoaRepository;
import com.fabricio.OS.repositories.TecnicoRepository;
import com.fabricio.OS.services.exceptions.DataIntegratyViolationException;
import com.fabricio.OS.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

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

    public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
        //Validando o CPF
        //O método findById já verifica se existe um técnico com esse ID
        Tecnico tecnicoAserAtualizado = findById(id);

        if(findByCpf(tecnicoDTO) != null && findByCpf(tecnicoDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado no sistema!");
        }

        tecnicoAserAtualizado.setNome(tecnicoDTO.getNome());
        tecnicoAserAtualizado.setCpf(tecnicoDTO.getCpf());
        tecnicoAserAtualizado.setTelefone(tecnicoDTO.getTelefone());

        return tecnicoRepository.save(tecnicoAserAtualizado);
    }

    public void delete(Integer id) {
        Tecnico obj = this.findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Técnico possui ordens der serviço, não pode ser deletado!");
        }

        tecnicoRepository.deleteById(id);
    
    }


    public Pessoa findByCpf(TecnicoDTO tecnicoDTO){
        Pessoa obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

        if(obj != null){
            return obj;
        }
        return null;
    }


}
