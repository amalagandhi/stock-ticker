package com.amala.stockticker.controller;

import com.amala.stockticker.model.StockPriceEvent;
import com.amala.stockticker.service.StockPriceCacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockPriceController {

    private final StockPriceCacheService cacheService;

    public StockPriceController(StockPriceCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/{symbol}")
    public StockPriceEvent getLatestPrice(@PathVariable String symbol) {
        return cacheService.getLatestPrice(symbol);
    }
}