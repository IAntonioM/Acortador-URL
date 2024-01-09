package com.example.AcortarUrl.persistence.repository;

import com.example.AcortarUrl.persistence.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long> {
    ShortUrl findByUuid(String uuid);
}
