package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


interface GeneroJpaRepository extends JpaRepository<Genero, Integer>, JpaSpecificationExecutor<Genero>{
    
    @Query("SELECT MAX(p.id) +1  FROM Genero p")
    Integer findMaxID();
    
}
