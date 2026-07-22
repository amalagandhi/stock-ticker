package com.amala.stockticker.service;

import com.amala.stockticker.model.StockPriceEvent;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockPriceCacheService {

    private static final String CACHE_KEY = "stock-prices";

    private final RedisTemplate<String, StockPriceEvent> redisTemplate;
    private final HashOperations<String, String, StockPriceEvent> hashOperations;

    public StockPriceCacheService(RedisTemplate<String, StockPriceEvent> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void cacheLatestPrice(StockPriceEvent event) {
        hashOperations.put(
                CACHE_KEY,
                event.getSymbol(),
                event
        );
    }

    public StockPriceEvent getLatestPrice(String symbol){
        return hashOperations.get(CACHE_KEY, symbol);

    }
}