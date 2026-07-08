package com.amala.stockticker.producer;

import com.amala.stockticker.model.StockPriceEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

/*
 * ============================================================================
 * CLASS: StockPriceProducer
 * ============================================================================
 *
 * RESPONSIBILITY
 * --------------
 * Produces stock price events every 2 seconds and publishes them to the
 * Kafka topic "stock-prices".
 *
 * WHERE THIS CLASS FITS
 * ---------------------
 *          Scheduler
 *              │
 *              ▼
 *      StockPriceProducer
 *              │
 *              ▼
 *      Kafka Topic: stock-prices
 *
 * This class knows ONLY how to publish events.
 * It has no knowledge of who consumes them.
 *
 * DESIGN PRINCIPLE
 * ----------------
 * Loose Coupling
 *
 * Producers and consumers communicate through Kafka instead of calling
 * each other directly.
 */
/*
 * ============================================================================
 * CONCEPT: Spring Bean
 * ============================================================================
 *
 * @Service tells Spring that this class should be managed by the
 * ApplicationContext.
 *
 * During startup:
 *
 * SpringApplication.run()
 *          │
 *          ▼
 * Component Scan
 *          │
 *          ▼
 * Finds @Service
 *          │
 *          ▼
 * Creates ONE StockPriceProducer object
 *
 * Other Spring beans receive this object through Dependency Injection.
 */


/*
 * @Service - Marks this class as a Spring Bean.
 *
 * Spring discovers this class during Component Scanning,
 * creates one managed instance, and stores it in the
 * ApplicationContext.
 *
 * This object is then available for Dependency Injection.
 */
@Service
public class StockPriceProducer {

    /*
     * Constructor Injection
     *
     * KafkaTemplate is automatically injected by Spring.
     *
     * Why?
     *
     * 1. spring-kafka dependency exists.
     * 2. application.yml configures Kafka.
     * 3. Spring Boot Auto Configuration creates KafkaTemplate.
     * 4. Spring injects it into this constructor.
     *
     * Constructor Injection is preferred because:
     * - dependencies are explicit
     * - fields can be final
     * - easier to unit test
     */
    private final KafkaTemplate<String, StockPriceEvent> kafkaTemplate;
    private final Random random = new Random();
    private static final String[] SYMBOLS = {"AAPL", "MSFT", "GOOGL",
            "AMZN","NVDA"};

    public StockPriceProducer(KafkaTemplate<String, StockPriceEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Every 2 seconds Spring automatically invokes this method.
     *
     * We never call publishStockPrice() ourselves.
     *
     * Spring Scheduler manages:
     * - timer
     * - background thread
     * - invocation
     */
    @Scheduled(fixedRate = 2000)
    public void publishStockPrice() {

        String symbol = SYMBOLS[new Random().nextInt(SYMBOLS.length)];
        StockPriceEvent event = new StockPriceEvent(
                symbol,
                150 + random.nextDouble() * 50,
                Instant.now()
        );

        /*
         * Sends an event to the Kafka topic.
         *
         * Producer Flow:
         *
         * StockPriceEvent
         *        │
         *        ▼
         * JsonSerializer
         *        │
         *        ▼
         * Kafka Topic
         *
         * Kafka stores bytes, not Java objects.
         * Spring automatically serializes this object into JSON.
         */
        kafkaTemplate.send(
                "stock-prices",
                event.getSymbol(),
                event
        );

        System.out.println(
                "Published: " +
                        event.getSymbol() +
                        " -> " +
                        event.getPrice()
        );
    }
}
