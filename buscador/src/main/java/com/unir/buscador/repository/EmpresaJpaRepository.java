package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


interface EmpresaJpaRepository extends JpaRepository<Empresa, Integer>, JpaSpecificationExecutor<Empresa>{
    
    @Query("SELECT MAX(p.id) +1  FROM Empresa p")
    Integer findMaxID();
    
}
