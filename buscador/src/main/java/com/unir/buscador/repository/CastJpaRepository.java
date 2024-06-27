package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface CastJpaRepository extends JpaRepository<Cast, Integer> , JpaSpecificationExecutor<Cast> {
    
    
}
