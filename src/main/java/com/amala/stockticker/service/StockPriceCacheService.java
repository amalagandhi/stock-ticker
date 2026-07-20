package com.amala.stockticker.service;

import com.amala.stockticker.model.StockPriceEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockPriceCacheService {

    private static final String CACHE_KEY = "stock-prices";

    private final RedisTemplate<String, Object> redisTemplate;

    public StockPriceCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheLatestPrice(StockPriceEvent event) {
        redisTemplate.opsForHash().put(
                CACHE_KEY,
                event.getSymbol(),
                event
        );
    }
}