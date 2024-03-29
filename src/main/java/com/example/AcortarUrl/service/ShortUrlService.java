package com.example.AcortarUrl.service;

import com.example.AcortarUrl.dto.ShortUrlRequest;
import com.example.AcortarUrl.dto.ShortUrlResponse;
import com.example.AcortarUrl.mapper.ShortUrlMapper;
import com.example.AcortarUrl.persistence.entity.ShortUrl;
import com.example.AcortarUrl.persistence.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShortUrlService {
    @Value("${url.host}")
    private String urlHost;
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlMapper shortUrlMapper;
    @Autowired
    public ShortUrlService(ShortUrlRepository shortUrlRepository, ShortUrlMapper shortUrlMapper) {
        this.shortUrlRepository = shortUrlRepository;
        this.shortUrlMapper = shortUrlMapper;
    }

    public List<ShortUrlResponse> getAll() {
        List<ShortUrl> urls = shortUrlRepository.findAll();
        return urls.stream()
                .map(url -> shortUrlMapper.toShortUrlResponse(url,urlHost))
                .collect(Collectors.toList());
    }
    public ShortUrl getShortUrlToUuid(String uuid){
            return shortUrlRepository.findByUuid(uuid);
    }
    public ShortUrlResponse save(ShortUrlRequest request){
        ShortUrl shortUrl = shortUrlMapper.toShortUrl(request);
        ShortUrl saved = shortUrlRepository.save(shortUrl);
        return shortUrlMapper.toShortUrlResponse(saved,urlHost);
    }

    public void delete(Long id) {
        shortUrlRepository.deleteById(id);
    }

    public ShortUrlResponse update(Long id, ShortUrlRequest shortUrlRequest) {
        ShortUrl existingUrl = shortUrlRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found with id: " + id));
        existingUrl.setUrl(shortUrlRequest.getUrl());
        ShortUrl updatedUrl = shortUrlRepository.save(existingUrl);
        return shortUrlMapper.toShortUrlResponse(updatedUrl, urlHost);
    }
}
