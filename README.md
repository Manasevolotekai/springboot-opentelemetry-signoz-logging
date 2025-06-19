# Spring Boot + OpenTelemetry + SigNoz (with Docker)

This project demonstrates centralized logging using Spring Boot 3.5.0, OpenTelemetry Collector, and SigNoz (with MySQL replaced by PostgreSQL and ClickHouse as the storage engine). It is fully Dockerized.

---

## 🚀 Project Structure

```
sipstrlogdemo/
├── backend/                       # Spring Boot app source code
├── docker-compose.yml            # Docker services
├── Dockerfile                    # Dockerfile for Spring Boot app
├── otel-collector-config.yaml    # OpenTelemetry Collector config
├── frontend-env.js               # Config for SigNoz frontend
└── README.md                     # You are here
```

---

## 📦 Prerequisites

* Docker Desktop installed (running)
* Git Bash or similar terminal on Windows

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Manasevolotekai/springboot-opentelemetry-signoz-logging.git
cd springboot-opentelemetry-signoz-logging
```

### 2. Start All Services

Run this from Git Bash:

```bash
docker compose down -v
docker compose up -d --build
```

This will:

* Build the Spring Boot app
* Start PostgreSQL, ClickHouse, OpenTelemetry Collector, SigNoz services
* Start the Spring Boot backend service inside Docker

### 3. Verify Spring Boot Started

```bash
docker logs -f backend-service
```

You should see logs like:

```
... Starting SipstrlogdemoApplication ...
Tomcat started on port 8081 (http) ...
Started SipstrlogdemoApplication in ... seconds ...
```

---

## ✅ How to Hit APIs

Open Swagger UI at:

```
http://localhost:8081/swagger-ui/index.html
```

Use endpoints like:

* `POST /api/users` — Create a user

```json
{
  "name": "John",
  "email": "john@example.com"
}
```

* `GET /api/users/1` — Fetch a user
* `PUT /api/users/1` — Update a user
* `DELETE /api/users/1` — Delete a user

---

## 📊 View Logs in SigNoz

1. Open SigNoz UI:

```
http://localhost:3301
```

2. Log in or Sign up (if not already done)
3. Go to **Logs Explorer** tab
4. Filter by `serviceName = sipstrlogdemo`
5. You should see logs from your Spring Boot application

---

## ⚙️ Tech Stack

* Spring Boot 3.5.0
* OpenTelemetry Java Agent
* PostgreSQL (for metadata)
* ClickHouse (for logs & traces)
* SigNoz
* Docker Compose

---

## 🔍 Debugging Tips

* View backend logs:

```bash
docker logs -f backend-service
```

* Check collector logs:

```bash
docker logs -f otel_collector_sipstr
```

* Check query-service health:

```bash
curl http://localhost:8080/api/v1/health
```

---

## 📮 Contact

Reach out to [@Manasevolotekai](https://github.com/Manasevolotekai) for questions or issues.

---

## ✅ Status

* [x] Spring Boot app runs inside Docker
* [x] API works via Swagger
* [x] Logs are shipped to SigNoz using OTEL Collector
* [ ] Signup flow in SigNoz UI is being fixed
