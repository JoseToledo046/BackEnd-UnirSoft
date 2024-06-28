package com.unir.buscador.controller;

import com.unir.buscador.model.pojo.Video;
import com.unir.buscador.model.pojo.VideoDto;
import com.unir.buscador.model.request.CreateVideoRequest;
import com.unir.buscador.service.VideoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/videos")
    public List<Video> getVideo(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer id_film,
            @RequestParam(required = false) String id_video,
            @RequestParam(required = false) String plataforma,
            @RequestParam(required = false) String name_video) {

        List<Video> video = videoService.getVideoL(id, id_film, id_video, plataforma, name_video);
        return video;

    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Video> getVideoID(@PathVariable Integer id) {

        Video video = videoService.getVideo(id);

        if (video != null) {
            return ResponseEntity.ok(video);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/videos")
    public ResponseEntity<Video> addVideo(@RequestBody CreateVideoRequest request) {

        Video creaCast = videoService.createVideo(request);
        if (creaCast != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creaCast);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Integer id) {

        Boolean removed = videoService.removeVideo(id);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/videos/{id}")
    public ResponseEntity<Video> patchVideo(@PathVariable Integer id,
            @RequestBody String patchBody) {

        Video patched = videoService.updateVideoP(id, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/videos/{id}")
    public ResponseEntity<Video> putVideo(@PathVariable Integer id,
            @RequestBody VideoDto request) {

        Video updated = videoService.updateVideo(id, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
