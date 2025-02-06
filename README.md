# Microservices-test
1️⃣ Levantar toda la infraestructura (Base + Kafka + Microservicios):

docker-compose -f docker/docker-compose.base.yml -f docker/docker-compose.kafka.yml -f docker/docker-compose.services.yml up --build -d

2️⃣ Si solo necesitas base + servicios (sin Kafka)

docker-compose -f docker/docker-compose.base.yml -f docker/docker-compose.services.yml up --build -d

3️⃣ Levantar solo Kafka y Zookeeper:

docker-compose -f docker/docker-compose.kafka.yml up -d

4️⃣ Apagar toda la infraestructura:

docker-compose -f docker/docker-compose.base.yml -f docker/docker-compose.kafka.yml -f docker/docker-compose.services.yml down -v


📌 Construir imágenes manualmente

docker build -t auth-service ./auth-service
docker build -t customer-service ./customer-service
docker build -t account-service ./account-service
docker build -t api-gateway ./api-gateway
docker build -t service-registry ./service-registry


docker run -d --name zookeeper -p 2181:2181 zookeeper:3.4.9
docker run -d --name kafka -p 9092:9092 --link zookeeper wurstmeister/kafka:2.12-2.2.1

docker run -d --name kafka -p 9092:9092 --link zookeeper \
-e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
wurstmeister/kafka:2.12-2.2.1