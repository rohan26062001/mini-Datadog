# Kafka Bootstrap Server Configuration Insight

While integrating Kafka topic auto-creation using `NewTopic`, repeated `AdminMetadataManager` rebootstrap logs started appearing. The issue occurred because only `spring.kafka.producer.bootstrap-servers=localhost:29092` was configured, which is used specifically by Kafka producers (`KafkaTemplate`).

However, Spring internally creates a `KafkaAdmin` client for managing topics (`NewTopic` beans). `KafkaAdmin` does not use producer-specific configuration. Since `spring.kafka.bootstrap-servers` was not configured, it attempted to connect using the default Kafka port (`localhost:9092`), causing repeated connection retries.

Fix:

```properties
spring.kafka.bootstrap-servers=localhost:29092
```

Difference:

* `spring.kafka.bootstrap-servers` → shared/global Kafka connection config used by admin, producer, and consumer clients.
* `spring.kafka.producer.bootstrap-servers` → producer-only configuration.

# Kafka Message Key and Partitioning Notes

When publishing messages using:

```java id="nt1"
send(topic, key, value)
```

Kafka does not guarantee that different keys will go to different partitions. Instead, Kafka guarantees that the same key will always map to the same partition (as long as partition count remains unchanged).

Kafka internally uses a hashing strategy similar to:

```text id="nt2"
hash(key) % number_of_partitions
```

Example with 3 partitions:

* `payment-service` → Partition 1
* `auth-service` → Partition 0
* `order-service` may also map to Partition 1

So multiple different keys can still end up in the same partition.

The main advantage of using a key like `serviceName` is ordering. Kafka guarantees ordering only within a partition. Therefore, all logs from the same service remain ordered because they consistently go to the same partition.

Without a key (`key = null`), Kafka distributes messages round-robin across partitions, which may break ordering for related events.

# Kafka Message Retention and Consumer Behavior Notes

While consuming messages using:

```bash id="kn1"
kafka-console-consumer --from-beginning
```

older log events from previous runs were still visible even though Docker volumes were not explicitly configured.

This happened because stopping/restarting containers does not always remove container filesystem data. Kafka data can still persist as long as the container itself exists. Additionally, Kafka is designed as an append-only event log, not a consume-and-delete queue.

The flag:

```bash id="kn2"
--from-beginning
```

tells the consumer to read messages from the earliest available offset in the topic, including old retained events.

Important distinctions:

* `docker-compose stop` → stops containers but preserves container filesystem/data.
* `docker-compose down` → removes containers and networks.
* Kafka retains messages for a configured retention period even after consumption.

Without `--from-beginning`, consumers typically read only newly arriving messages.
