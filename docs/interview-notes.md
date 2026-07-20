# Stock Ticker - Interview Notes

These notes summarize the key backend and distributed systems concepts learned while building this project.

---

# Kafka

## Topics

- A topic is a logical stream of messages.
- Producers publish messages to a topic.
- Consumers subscribe to a topic.

---

## Partitions

- A topic is divided into one or more partitions.
- Partitions enable horizontal scalability.
- Each partition is an append-only log.

Remember:

> Ordering is guaranteed only within a partition.

---

## Offsets

- Every message has an offset.
- Offsets are unique only within a partition.
- Each partition maintains its own sequence of offsets.
- Consumers track offsets to resume processing after a restart.

---

## Message Keys

The producer sends messages using:

```java
kafkaTemplate.send(topic, key, value);
```

Kafka hashes the key to determine the partition.

Benefits:

- Same key consistently maps to the same partition.
- Preserves ordering for related messages.

---

## Consumer Groups

- Consumers in the same group share the work.
- A partition is assigned to only one consumer within a consumer group.
- Multiple consumer groups can independently consume the same topic.

---

## ConsumerRecord Metadata

Useful metadata available to consumers:

- Topic
- Partition
- Offset
- Key
- Timestamp

---

## Why Kafka?

Kafka is designed for:

- High throughput
- Fault tolerance
- Horizontal scalability
- Event-driven architectures

---

# Redis

## Why Redis?

Redis is used to store the latest stock price for each symbol.

Benefits:

- Very fast lookups
- In-memory storage
- Ideal for caching frequently accessed data

Example:

NVDA → Latest Price

instead of replaying all Kafka events.

---

# General Design Principles

- Separate responsibilities into focused classes and services.
- Keep messaging logic separate from caching logic.
- Build components that can scale independently.