# spring-boot-microservices-new
# 🛒 Order Delivery System - Microservices Architecture

## 📌 Overview
This project is a **Microservices-based Order Delivery System** designed to simulate a real-world e-commerce backend.  
It demonstrates **service orchestration, inter-service communication, fault tolerance, and security** using modern tools and practices.

---

## ⚙️ Architecture

The system is built with multiple independent services, connected via both synchronous (REST) and asynchronous (Kafka) communication.  
Requests are routed through an **API Gateway** and services are dynamically discovered using **Eureka Server**.

### 🏗️ Services
1. **API Gateway**  
   - Routes client requests to microservices.  
   - Handles authentication forwarding and load balancing.  

2. **Eureka Discovery Server**  
   - Enables dynamic registration and discovery of services.  

3. **Product Service**  
   - Manages product catalog (CRUD operations).  
   - Tech: Spring Boot, JPA, MySQL.  

4. **Inventory Service**  
   - Tracks and updates stock availability.  
   - Sync communication with Order Service.  

5. **Order Service**  
   - Manages order placement and validation.  
   - Communicates with Inventory Service (sync) and Notification Service (async).  
   - Implements **Resilience4j** for circuit breaker, retry, and fallback.  

6. **Notification Service**  
   - Listens to Kafka events and sends order confirmations/updates.  

---

## 🔄 Communication Flow

- **Synchronous (REST/WebClient):**  
  `Order Service ↔ Inventory Service` (real-time stock validation).  

- **Asynchronous (Kafka):**  
  `Order Service → Notification Service` (event-driven notifications).  

---

## 🔐 Security
- Integrated **Keycloak** for centralized Authentication & Authorization.  
- Implemented **Role-Based Access Control (RBAC)** using JWT tokens.  

---

## 📊 Observability
- Used **Zipkin** for distributed tracing to monitor request flow across services.  
- Logged service uptime and request metrics.  

---

## 🛡️ Resilience
- Applied **Resilience4j** in Order Service for:  
  - Circuit Breaker  
  - Retry Mechanism  
  - Fallback Handling  

This prevents cascading failures when Inventory Service is down.  

---

## 📦 Deployment
- **Dockerized all services** for easy deployment and scalability.  
- Each service runs as an independent container.  

---

## 🚀 Tech Stack
- **Backend:** Spring Boot, Spring Cloud, JPA, Hibernate  
- **Database:** MySQL  
- **Messaging:** Apache Kafka  
- **Security:** Keycloak (JWT, RBAC)  
- **Resilience:** Resilience4j  
- **Tracing:** Zipkin  
- **Containerization:** Docker  

---

## 📈 Key Features
✔️ Scalable Microservices Architecture  
✔️ API Gateway + Service Discovery  
✔️ Sync + Async communication model  
✔️ Secure with Keycloak Authentication  
✔️ Fault-Tolerant with Resilience4j  
✔️ Observability with Zipkin  
✔️ Dockerized Deployment  

---

## 📝 How to Run
1. Clone the repository.  
2. Start **Eureka Server**.  
3. Start **API Gateway**.  
4. Start all microservices (`Product`, `Inventory`, `Order`, `Notification`).  
5. Start **Kafka Broker** and **Zookeeper**.  
6. Access system via API Gateway.  

---

## 📌 Future Enhancements
- Add **Payment Service** with async event handling.  
- Integrate **Kubernetes** for orchestration.  
- Implement **Monitoring dashboards** (Grafana + Prometheus).  

