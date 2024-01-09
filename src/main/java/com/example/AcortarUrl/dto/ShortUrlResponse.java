package com.example.AcortarUrl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrlResponse {
    private Long id;
    private String url;
    private String uuid;
    private String shortUrl;
    private Date created;
}
