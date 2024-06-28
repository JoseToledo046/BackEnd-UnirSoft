package com.unir.buscador.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.buscador.model.pojo.Video;
import com.unir.buscador.model.pojo.VideoDto;
import com.unir.buscador.model.request.CreateVideoRequest;
import com.unir.buscador.repository.VideoRepository;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Video> getVideoL(
            Integer id,
            Integer id_film,
            String id_video,
            String plataforma,
            String name_video) {

        if (id != null
                || id_film != null
                || StringUtils.hasLength(id_video)
                || StringUtils.hasLength(plataforma)
                || StringUtils.hasLength(name_video)) {
            return repository.search(id, id_film, id_video, plataforma, name_video);
        }

        List<Video> video = repository.getVideo();
        return video.isEmpty() ? null : video;

    }

    @Override
    public Video getVideo(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Video createVideo(CreateVideoRequest request) {

        if (request != null) {
            
            request.setId(repository.maxID());

            Video video;
            video = Video.builder()
                    .id(request.getId())
                    .id_film(request.getId_film())
                    .id_video(request.getId_video())
                    .plataforma(request.getPlataforma())
                    .name_video(request.getName_video())
                    .build();

            return repository.save(video);

        } else {
            return null;
        }

    }

    @Override
    public Boolean removeVideo(Integer id) {

        Video video = repository.findById(id);

        if (video != null) {
            repository.delete(video);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    @Override
    public Video updateVideoP(Integer id, String updRequest) {
        
        Video video = repository.findById(id);
        
        if (video != null) {
            
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(video)));
                Video patched = objectMapper.treeToValue(target, Video.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating pelicula {}", id, e);
                return null;
            }
            
        } else {
            return null;
        }
        
    }

    @Override
    public Video updateVideo(Integer id, VideoDto updRequest) {
        
        Video video = repository.findById(id);
        
        if (video != null) {
            video.update(updRequest);
            repository.save(video);
            return video;
        } else {
            return null;
        }
        
    }

}
