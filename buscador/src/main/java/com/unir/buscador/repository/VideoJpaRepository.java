package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


interface VideoJpaRepository extends JpaRepository<Video, Integer>, JpaSpecificationExecutor<Video>{
    
    @Query("SELECT MAX(p.id) +1  FROM Video p")
    Integer findMaxID();
    
}
