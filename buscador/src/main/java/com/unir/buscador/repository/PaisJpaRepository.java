package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


interface PaisJpaRepository extends JpaRepository<Pais, Integer>, JpaSpecificationExecutor<Pais>{
    
    @Query("SELECT MAX(p.id) +1  FROM Pais p")
    Integer findMaxID();
    
}
