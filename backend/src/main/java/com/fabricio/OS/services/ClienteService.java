package com.fabricio.OS.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabricio.OS.domain.Cliente;
import com.fabricio.OS.domain.Pessoa;
import com.fabricio.OS.dtos.ClienteDTO;
import com.fabricio.OS.repositories.ClienteRepository;
import com.fabricio.OS.repositories.PessoaRepository;
import com.fabricio.OS.services.exceptions.DataIntegratyViolationException;
import com.fabricio.OS.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID : " + id + ", Tipo: " + Cliente.class.getName()));

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO){
        //verificando se já exisite um usu´ário com o CPF cadastrado
        if(this.findByCpf(clienteDTO) != null){
            throw new  DataIntegratyViolationException("CPF já cadastrado no sistema!");
        }
        

        Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone());
        return clienteRepository.save(cliente);
        
    }

    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
        //Validando o CPF
        //O método findById já verifica se existe um cliente com esse ID
        Cliente clienteAserAtualizado = findById(id);

        if(findByCpf(clienteDTO) != null && findByCpf(clienteDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado no sistema!");
        }

        clienteAserAtualizado.setNome(clienteDTO.getNome());
        clienteAserAtualizado.setCpf(clienteDTO.getCpf());
        clienteAserAtualizado.setTelefone(clienteDTO.getTelefone());

        return clienteRepository.save(clienteAserAtualizado);
    }

    public void delete(Integer id) {
        Cliente obj = this.findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Cliente possui ordens der serviço, não pode ser deletado!");
        }

        clienteRepository.deleteById(id);
    
    }


    public Pessoa findByCpf(ClienteDTO clienteDTO){
        Pessoa obj = pessoaRepository.findByCpf(clienteDTO.getCpf());

        if(obj != null){
            return obj;
        }
        return null;
    }


}
