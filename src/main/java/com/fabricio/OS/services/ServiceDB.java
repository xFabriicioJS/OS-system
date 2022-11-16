package com.fabricio.OS.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabricio.OS.domain.Cliente;
import com.fabricio.OS.domain.OS;
import com.fabricio.OS.domain.Tecnico;
import com.fabricio.OS.domain.enums.Prioridade;
import com.fabricio.OS.domain.enums.Status;
import com.fabricio.OS.repositories.ClienteRepository;
import com.fabricio.OS.repositories.OSRepository;
import com.fabricio.OS.repositories.TecnicoRepository;

@Service    
public class ServiceDB {
    
    
    @Autowired
	TecnicoRepository tecnicoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	OSRepository osRepository;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Fabricio Monteiro", "736.054.540-46", "11 98775-7676");
		Cliente c1 = new Cliente(null, "Cliente Fabricio","164.605.620-59", "11 98786-9851");

		OS o1 = new OS(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

		t1.getList().add(o1);
		c1.getList().add(o1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.save(c1);
		osRepository.save(o1);
    }
}
