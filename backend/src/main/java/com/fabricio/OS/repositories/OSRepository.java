package com.fabricio.OS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabricio.OS.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{
    
}
