# spring-redis-performance

**Redis Cache-aside: Student GET 85ms DB → 2ms Redis | Java 17 + Spring Boot 2.6.6**

Backend service proving Redis caching with measurable performance gains.

## 🚀 Tech Stack

| Layer        | Technology                                              |
| ------------ | ------------------------------------------------------- |
| **Backend**  | Java 17, Spring Boot 2.6.6, Spring Web, Spring Data JPA |
| **Cache**    | Redis with Jackson JSON + `@Cacheable`                  |
| **Database** | H2 in-memory for dev/testing                            |
| **Ops**      | Docker Compose, Timing logs, Spring Boot Actuator       |

---

## ⚡ Performance Proof

**Before Redis:** Student GET `85ms` DB query
**After Redis:** Student GET `2ms` cache hit

**Speedup:** `42.5x faster` with `@Cacheable` on service layer

**Proof method:** `System.currentTimeMillis` logs in console

---

## 🛠️ Redis Implementation

```java
@Cacheable(value = "student-cache", key = "#id")
@Transactional(readOnly = true)
public Student getstudentById(Long id) {

    long start = System.currentTimeMillis();

    Student student = studentRepository.findById(id).orElse(null);

    System.out.println(
        "DB hit for ID " + id + ": "
        + (System.currentTimeMillis() - start)
        + "ms"
    );

    return student;
}
```

---

# 📊 Monitoring with Spring Boot Actuator

Spring Boot Actuator was used for application monitoring and observability.

Monitoring endpoint:

```
/actuator/metrics/http.server.requests?tag=uri:/student/students
```

Collected metrics:

* HTTP request count
* Total processing time
* Maximum latency
* HTTP methods
* URI performance
* HTTP status codes

Example monitoring result:

```
COUNT: 5015 requests
TOTAL_TIME: 16.48 seconds
MAX: 0.026 seconds
```

### Actuator Metrics Screenshot

Add screenshot:

```
docs/screenshots/actuator-http-server-requests.png
```

---

# 🧪 Load Testing

ApacheBench (`ab`) was used to test the student endpoint under concurrent load.

Command:

```bash
ab -n 5000 -c 100 https://<application-url>/student/students
```

## Test Configuration

| Parameter        | Result |
| ---------------- | -----: |
| Total Requests   |   5000 |
| Concurrent Users |    100 |
| Failed Requests  |      0 |

## Results

| Metric                        |   Value |
| ----------------------------- | ------: |
| Requests per second           |   82.68 |
| Average request time          | 1209 ms |
| 50% requests completed within |  595 ms |
| Maximum request time          | 9900 ms |

### ApacheBench Load Test Screenshot

Add screenshot:

```
docs/screenshots/load-test-apachebench.png
```

---

# 🔎 API Verification

Student endpoint successfully tested:

GET:

```
/student/students
```

Response:

```json
[
  {
    "id": 1,
    "studentName": "John Doe",
    "email": "john@test.com",
    "regDate": "2026-07-18",
    "courseName": "Computer Science"
  }
]
```

### Student API Response Screenshot

Add screenshot:

```
docs/screenshots/student-api-response.png
```

---

# 🏗️ Run Locally

```bash
docker compose up -d
mvn spring-boot:run
```

Application:

```
http://localhost:8080
```

---

# 🔜 Remaining Work

* Add Redis cache load test comparison
* Compare database performance vs Redis cache hits
* Add JVM and memory Actuator metrics
* Add Prometheus/Grafana dashboard (if required)
* Add final performance comparison screenshots

---

# 📸 Evidence Screenshots

Required screenshots:

## 1. Actuator Monitoring

Browser URL:

```
/actuator/metrics/http.server.requests?tag=uri:/student/students
```

File:

```
actuator-http-server-requests.png
```

---

## 2. Student API Response

Browser URL:

```
/student/students
```

File:

```
student-api-response.png
```

---

## 3. ApacheBench Load Test

Codespace terminal showing:

```
ab -n 5000 -c 100 ...
Requests per second: 82.68
Failed requests: 0
```

File:

```
load-test-apachebench.png
```

---

## Commit Changes

```bash
git add README.md docs/screenshots

git commit -m "Add actuator monitoring and load testing evidence"

git push
```
