package com.unir.buscador.repository;

import com.unir.buscador.model.pojo.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

interface PeliculaJpaRepository extends JpaRepository<Pelicula, Integer>, JpaSpecificationExecutor<Pelicula> {

    List<Pelicula> findByTitle(String title);

    @Query("SELECT MAX(p.id) +1  FROM Pelicula p")
    Integer findMaxID();
    
}