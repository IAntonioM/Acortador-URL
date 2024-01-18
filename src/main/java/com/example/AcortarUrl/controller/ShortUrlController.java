package com.example.AcortarUrl.controller;

import com.example.AcortarUrl.dto.ShortUrlRequest;
import com.example.AcortarUrl.dto.ShortUrlResponse;
import com.example.AcortarUrl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/acortador")
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping
    public ResponseEntity<List<ShortUrlResponse>> obtenerAcortadorUrls(){
        List<ShortUrlResponse> urls = shortUrlService.getAll();

        return ResponseEntity.ok(urls);
    }

    @PostMapping
    public ResponseEntity<ShortUrlResponse> crearAcortadorUrl(@RequestBody ShortUrlRequest shortUrlRequest){
        ShortUrlResponse url = shortUrlService.save(shortUrlRequest);
        return ResponseEntity.created(URI.create(url.getShortUrl())).body(url);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAcortadorUrl(@PathVariable Long id) {
        shortUrlService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShortUrlResponse> editarAcortadorUrl(@PathVariable Long id,
                                                               @RequestBody ShortUrlRequest shortUrlRequest) {
        ShortUrlResponse url = shortUrlService.update(id, shortUrlRequest);
        return ResponseEntity.ok(url);
    }

}
