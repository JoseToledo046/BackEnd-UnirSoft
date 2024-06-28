package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Video;
import com.unir.buscador.model.pojo.VideoDto;
import com.unir.buscador.model.request.CreateVideoRequest;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface VideoService {
    
    List<Video> getVideoL(
            Integer id,
            Integer id_film,
            String id_video,
            String plataforma,
            String name_video);

    Video getVideo(Integer id);

    Video createVideo(CreateVideoRequest request);

    Boolean removeVideo(Integer id);

    Video updateVideoP(Integer id, String updRequest);

    Video updateVideo(Integer id, VideoDto updRequest);
    
}
