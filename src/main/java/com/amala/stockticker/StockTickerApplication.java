package com.amala.stockticker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/*
 * =============================================================================
 * CLASS: StockTickerApplication
 * =============================================================================
 *
 * RESPONSIBILITY
 * --------------
 * This is the entry point of the Spring Boot application.
 *
 * Execution starts from the main() method. Spring Boot then creates the
 * ApplicationContext, performs component scanning, creates all Spring Beans,
 * performs Dependency Injection, starts the embedded Tomcat server, and
 * initializes background services such as the Scheduler and Kafka listeners.
 *
 * APPLICATION STARTUP FLOW
 * ------------------------
 *
 * main()
 *      │
 *      ▼
 * SpringApplication.run()
 *      │
 *      ▼
 * Create ApplicationContext
 *      │
 *      ▼
 * Component Scan (@Service, @Component, @Controller ...)
 *      │
 *      ▼
 * Create Spring Beans
 *      │
 *      ▼
 * Perform Dependency Injection
 *      │
 *      ▼
 * Start Embedded Tomcat
 *      │
 *      ▼
 * Start Scheduler
 *      │
 *      ▼
 * Application Ready
 *
 * CONCEPTS
 * --------
 * - Spring Boot
 * - ApplicationContext
 * - Component Scanning
 * - Dependency Injection
 * - Auto Configuration
 */


/*
 * =====Spring Boot's primary annotation====.
 *
 * Combines:
 * 1. @Configuration
 * 2. @EnableAutoConfiguration
 * 3. @ComponentScan
 *
 * This annotation tells Spring Boot:
 * "Automatically configure my application and scan this package
 * (and all sub-packages) for Spring-managed beans."
 */
@SpringBootApplication

/*
 * Enables Spring's scheduling framework.
 *
 * During startup, Spring searches for every method annotated with
 * @Scheduled and registers it with the internal scheduler.
 *
 * Without this annotation, @Scheduled methods are ignored.
 */
@EnableScheduling
public class StockTickerApplication {

    /*
     * Java application entry point.
     *
     * SpringApplication.run() bootstraps the complete Spring application.
     *
     * Unlike a traditional Java application, we do not manually create
     * s here. Spring creates and manages application objects
     * (Beans) inside the ApplicationContext.
     */
    public static void main(String[] args) {
        SpringApplication.run(StockTickerApplication.class, args);
    }
}
