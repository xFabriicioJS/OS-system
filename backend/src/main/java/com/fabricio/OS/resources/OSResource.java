package com.fabricio.OS.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fabricio.OS.domain.OS;
import com.fabricio.OS.dtos.OSDTO;
import com.fabricio.OS.services.OSService;

@CrossOrigin("*")
@RestController
public class OSResource {
    
    @Autowired
    private OSService service;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
        OS obj = service.findById(id);

        return ResponseEntity.ok().body(new OSDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OSDTO> listDTO = service.findAll().stream().map((os) -> new OSDTO(os)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);   
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid OSDTO obj){
        obj = new OSDTO(service.create(obj));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO obj){
        obj = new OSDTO(service.update(obj));

        return ResponseEntity.ok().body(obj);
    }

}
