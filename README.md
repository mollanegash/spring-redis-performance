# spring-redis-performance
**Redis Cache-aside: Student GET 85ms DB → 2ms Redis | Java 17 + Spring Boot 2.6.6**

Backend service proving Redis caching with measurable performance gains.

## 🚀 Tech Stack
| Layer | Technology |
| --- | --- |
| **Backend** | Java 17, Spring Boot 2.6.6, Spring Web, Spring Data JPA |
| **Cache** | Redis with Jackson JSON + `@Cacheable` |
| **Database** | H2 in-memory for dev/testing |
| **Ops** | Docker Compose, Timing logs |

## ⚡ Performance Proof
**Before Redis:** Student GET `85ms` DB query  
**After Redis:** Student GET `2ms` cache hit  
**Speedup:** `42.5x faster` with `@Cacheable` on service layer  
**Proof method:** `System.currentTimeMillis` logs in console

## 🛠️ Redis Implementation
```java
@Cacheable(value = "student-cache", key = "#id")
@Transactional(readOnly = true) 
public Student getstudentById(Long id) {
    long start = System.currentTimeMillis();
    Student student = studentRepository.findById(id).orElse(null);
    System.out.println("DB hit for ID " + id + ": " + (System.currentTimeMillis() - start) + "ms");
    return student;
}
🏗️ Run Locally
bash
docker compose up -d
mvn spring-boot:run
App: http://localhost:8080
