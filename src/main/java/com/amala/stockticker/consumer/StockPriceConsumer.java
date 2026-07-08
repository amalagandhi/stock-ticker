/*
 * =============================================================================
 * CLASS: StockPriceConsumer
 * =============================================================================
 *
 * RESPONSIBILITY
 * --------------
 * Listens for new stock price events from Kafka and processes them.
 *
 * ARCHITECTURE
 * ------------
 *
 * Producer
 *      │
 *      ▼
 * Kafka Topic
 *      │
 *      ▼
 * StockPriceConsumer
 *
 * Unlike the producer, this class never polls Kafka directly.
 * Spring manages Kafka polling in the background and invokes
 * consume() whenever a new message arrives.
 */
package com.amala.stockticker.consumer;

import com.amala.stockticker.model.StockPriceEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockPriceConsumer {

    /*
     * Registers this method as a Kafka message listener.
     *
     * Spring creates a background listener container that continuously
     * polls Kafka.
     *
     * When a new message arrives:
     *
     * Kafka
     *    │
     *    ▼
     * JsonDeserializer
     *    │
     *    ▼
     * StockPriceEvent
     *    │
     *    ▼
     * consume(event)
     *
     * We never call this method ourselves.
     * Spring invokes it whenever Kafka delivers a new message.
     */
    @KafkaListener(
            topics = "stock-prices",
            groupId = "stock-consumer-group"
    )
    public void consume(StockPriceEvent event) {

        System.out.println("--------------------------------");

        System.out.println("Received Stock Event");

        System.out.println("Symbol    : " + event.getSymbol());

        System.out.println("Price     : " + event.getPrice());

        System.out.println("Timestamp : " + event.getTimestamp());

        System.out.println("--------------------------------");
    }
}
