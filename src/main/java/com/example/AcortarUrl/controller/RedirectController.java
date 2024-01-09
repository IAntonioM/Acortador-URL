package com.example.AcortarUrl.controller;

import com.example.AcortarUrl.persistence.entity.ShortUrl;
import com.example.AcortarUrl.service.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("")
public class RedirectController {
    private final ShortUrlService shortUrlService;

    @Autowired
    public RedirectController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/{uuid}")
    public void redirectUrl(@PathVariable String uuid, HttpServletResponse response) throws IOException {
        try {
            ShortUrl shortUrl = shortUrlService.getShortUrlToUuid(uuid);
            response.sendRedirect(shortUrl.getUrl());
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL no encontrada");
        }
    }
}
