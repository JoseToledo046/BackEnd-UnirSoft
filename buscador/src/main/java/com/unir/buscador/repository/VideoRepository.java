package com.unir.buscador.repository;

import com.unir.buscador.data.SearchCriteriaVideo;
import com.unir.buscador.data.SearchOperation;
import com.unir.buscador.data.SearchStatement;
import com.unir.buscador.model.pojo.Video;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VideoRepository {
    
    private final VideoJpaRepository repository;
    
    public List<Video> getVideo(){
        return repository.findAll();
    }
    
    public Video findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    
    public Video save(Video video){
        return repository.save(video);
    }
    
    public void delete(Video video){
        repository.delete(video);
    }
    
    public List<Video> search(
            Integer id,
            Integer id_film,
            String id_video,
            String plataforma,
            String name_video) {
        
        SearchCriteriaVideo<Video> spec;
        spec = new SearchCriteriaVideo<>();
        
        if (id != null) {
            spec.add(new SearchStatement("id", id, SearchOperation.EQUAL));
        }
        if (id_film != null) {
            spec.add(new SearchStatement("id_film", id_film, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(id_video)) {
            spec.add(new SearchStatement("id_video", id_video, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(plataforma)) {
            spec.add(new SearchStatement("plataforma", plataforma, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(name_video)) {
            spec.add(new SearchStatement("name_video", name_video, SearchOperation.MATCH));
        }
        
        return repository.findAll(spec);
        
    }
    
    public Integer maxID() {
        return repository.findMaxID();
    }
    
}
