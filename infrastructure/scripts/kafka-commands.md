# Kafka Utility Commands

This document contains commonly used Kafka commands for local development and debugging.

---

# 1. Start Kafka Infrastructure

From:

```bash
infrastructure/docker/
```

Run:

```bash
docker-compose up -d
```

---

# 2. Verify Running Containers

```bash
docker ps
```

---

# 3. Enter Kafka Container

```bash
docker exec -it <kafka-container-name> bash
```

Example:

```bash
docker exec -it docker-kafka-1 bash
```

---

# 4. Create Topic

```bash
kafka-topics --create \
   --topic logs \
   --bootstrap-server localhost:9092 \
   --partitions 1 \
   --replication-factor 1
```

---

# 5. List Topics

```bash
kafka-topics --list \
   --bootstrap-server localhost:9092
```

---

# 6. Consume Messages From Topic

```bash
kafka-console-consumer \
   --bootstrap-server localhost:9092 \
   --topic logs \
   --from-beginning
```

---

# 7. Stop Kafka Infrastructure

From:

```bash
infrastructure/docker/
```

Run:

```bash
docker-compose down
```

---

# Notes

* Kafka runs inside Docker containers.
* External applications should connect using:

```text
localhost:29092
```

* Internal Docker communication uses:

```text
kafka:9092
```