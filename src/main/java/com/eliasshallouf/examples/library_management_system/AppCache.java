package com.eliasshallouf.examples.library_management_system;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class AppCache {
    public static final String
        BOOK_CACHE = "book_cache",
        PATRON_CACHE = "patron_cache";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        cacheManager.setCaches(Arrays.asList(
            new ConcurrentMapCache(BOOK_CACHE),
            new ConcurrentMapCache(PATRON_CACHE)
        ));

        return cacheManager;
    }
}
