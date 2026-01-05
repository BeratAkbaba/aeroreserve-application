# AeroReserve Application

🔧 Tech Stack:
- Java 17+
- Spring Boot 3.x
- Spring Cloud Netflix (Eureka)
- API Gateway
- Config Server
- RESTful APIs
- Maven

## Architecture Overview
Bu uygulama mikroservis mimarisi ile geliştirilmiştir:
- **Eureka Server** – Service Discovery
- **Config Server** – Merkezi konfigürasyon yönetimi
- **API Gateway** – Tüm servis isteklerini yöneten gateway
- **User Service** – Kullanıcı yönetimi
- **Currency Exchange Service** – Para birimi dönüşümü

## How to Run
1. `eureka-server` ile başlat
2. `config-server` çalışsın
3. Diğer servisleri başlat
4. API Gateway üzerinden servisleri test et
