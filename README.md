9diMeak/
├── Dockerfile                              # Docker Image Configuration
├── docker-compose.yml                      # Container Orchestration (App + DB + n8n)
├── pom.xml                                 # Maven Dependencies
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/logistics/system/parcelmanagement/
│   │   │   ├── config/                     # Configuration (Security, Swagger, Mongo)
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/                 # REST Controllers (The "Waiters")
│   │   │   │   ├── AuthController.java
│   │   │   │   └── AdminController.java
│   │   │   ├── repository/                 # Data Access Layer (The "Pantry")
│   │   │   │   └── UserRepository.java
│   │   │   ├── dto/                        # Data Transfer Objects (The "Menu")
│   │   │   │   ├── request/
│   │   │   │   └── response/
│   │   │   ├── model/                      # MongoDB Documents (The "Ingredients")
│   │   │   │   ├── User.java
│   │   │   │   └── enums/
│   │   │   ├── mapper/                     # Mappers (DTO <-> Entity)
│   │   │   ├── service/                    # Business Logic (The "Chefs")
│   │   │   │   ├── impl/
│   │   │   │   └── interfaces/
│   │   │   ├── exception/                  # Global Exception Handling
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── security/                   # JWT Authentication Logic
│   │   │       └── JwtService.java
│   │   └── resources/
│   │       └── application.yml             # Main Configuration File
│   └── test/
│       ├── java/com/logistics/system/parcelmanagement/
│       │   └── unit/                       # Unit Tests (Mockito)
│       └── resources/