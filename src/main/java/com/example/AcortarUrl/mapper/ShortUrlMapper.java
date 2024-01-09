package com.example.AcortarUrl.mapper;

import com.example.AcortarUrl.dto.ShortUrlRequest;
import com.example.AcortarUrl.dto.ShortUrlResponse;
import com.example.AcortarUrl.persistence.entity.ShortUrl;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ShortUrlMapper{
    @Mappings({
            @Mapping(target = "shortUrl", expression = "java(hostUrl + shortUrl.getUuid())")
    })
    ShortUrlResponse toShortUrlResponse(ShortUrl shortUrl, @Context String hostUrl);


    @Mappings({
            @Mapping(target = "url", source = "request.url"),
            @Mapping(target = "uuid", expression = "java(generateUuid())"),
            @Mapping(target = "created", expression = "java(now())")
    })
    ShortUrl toShortUrl(ShortUrlRequest request);



    default String generateUuid() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    default Date now() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.of("America/Lima")).toInstant());
    }
}
