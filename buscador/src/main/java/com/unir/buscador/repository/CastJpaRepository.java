package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

interface CastJpaRepository extends JpaRepository<Cast, Integer> , JpaSpecificationExecutor<Cast> {
    
    @Query("SELECT MAX(p.id) +1  FROM Cast p")
    Integer findMaxID();
    
}
