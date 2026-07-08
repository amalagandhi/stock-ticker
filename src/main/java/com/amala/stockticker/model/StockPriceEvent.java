/*
 * =============================================================================
 * CLASS: StockPriceEvent
 * =============================================================================
 *
 * Represents a stock price event exchanged between services.
 *
 * This class is a Data Transfer Object (DTO).
 *
 * It contains no business logic.
 *
 * Its responsibility is to transport data between the Producer,
 * Kafka, and Consumer.
 *
 * Serialization Flow:
 *
 * Java Object
 *      │
 *      ▼
 * JsonSerializer
 *      │
 *      ▼
 * Kafka
 *      │
 *      ▼
 * JsonDeserializer
 *      │
 *      ▼
 * Java Object
 */
package com.amala.stockticker.model;

import java.time.Instant;

public class StockPriceEvent {

    private String symbol;
    private double price;
    private Instant timestamp;

    public StockPriceEvent() {
    }

    public StockPriceEvent(String symbol, double price, Instant timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

/*
 * =============================================================================
 * REVISION QUESTIONS
 * =============================================================================
 *
 * 1. What is a Spring Bean?
 * 2. Why is @Service needed?
 * 3. What is Dependency Injection?
 * 4. Why Constructor Injection over Field Injection?
 * 5. How is KafkaTemplate created?
 * 6. Why use Kafka instead of directly calling the consumer?
 * 7. How does @Scheduled work internally?
 */