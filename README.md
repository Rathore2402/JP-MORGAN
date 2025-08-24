# Midas
Project repo for the JPMC Advanced Software Engineering Forage program
# Midas Core - JPMC Forage Program

A Spring Boot financial transaction system developed as part of the JPMC Advanced Software Engineering Forage program. This application provides user management, balance tracking, and transaction processing capabilities with Kafka integration.

## 🚀 Features

- **User Management**: Create and manage users with balance tracking
- **Transaction Processing**: Send funds between users with validation
- **RESTful API**: Clean API endpoints for all operations
- **Kafka Integration**: Message streaming for transaction events
- **H2 Database**: In-memory database for development and testing
- **Spring Boot**: Modern Java framework with auto-configuration

## 🏗️ Architecture

- **Framework**: Spring Boot 3.2.5
- **Database**: H2 (in-memory)
- **Messaging**: Apache Kafka with Spring Kafka
- **API**: RESTful web services
- **Build Tool**: Maven
- **Java Version**: 17

## 📦 Project Structure

```
src/
├── main/java/com/jpmc/midascore/
│   ├── MidasCoreApplication.java    # Main application class
│   ├── entity/
│   │   └── UserRecord.java          # User entity with JPA annotations
│   ├── foundation/
│   │   ├── Balance.java             # Balance data transfer object
│   │   └── Transaction.java         # Transaction data transfer object
│   ├── repository/
│   │   └── UserRepository.java      # Spring Data JPA repository
│   └── component/
│       └── DatabaseConduit.java     # Database operations component
├── test/java/com/jpmc/midascore/
│   ├── TaskOneTests.java            # Task verification tests
│   ├── TaskTwoTests.java            # Additional task tests
│   ├── TaskThreeTests.java
│   ├── TaskFourTests.java
│   ├── TaskFiveTests.java
│   ├── BalanceQuerier.java          # Balance query utilities
│   ├── FileLoader.java              # File loading utilities
│   ├── KafkaProducer.java           # Kafka test utilities
│   └── UserPopulator.java           # User population utilities
└── test/resources/test_data/        # Test data files
```

## 🛠️ Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- (Optional) Kafka for messaging features

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Rathore2402/JP-MORGAN.git
   cd JP-MORGAN
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

### Running Tests

Execute all tests:
```bash
mvn test
```

Run specific task tests:
```bash
mvn test -Dtest=TaskOneTests
```

## 📋 API Endpoints

The application provides REST API endpoints for:

- **User Management**: Create, retrieve, and manage users
- **Balance Operations**: Check and update user balances
- **Transactions**: Process financial transactions between users

## 🔧 Configuration

Application configuration is managed through `application.yml`:
- Server port configuration
- Database settings (H2 by default)
- Kafka broker configuration

## 🧪 Testing

The project includes comprehensive test suites for:
- Application boot verification (TaskOneTests)
- Balance query operations
- Transaction processing
- Kafka message production/consumption
- User population and data loading

## 📊 Data Model

### UserRecord
- `id`: Auto-generated unique identifier
- `name`: User name (required)
- `balance`: Current account balance

### Transaction
- `senderId`: ID of the sending user
- `recipientId`: ID of the receiving user  
- `amount`: Transaction amount

### Balance
- `amount`: Current balance amount

## 🚦 Development Tasks

This project is structured around multiple development tasks as part of the JPMC forage program:

1. **Task One**: Application boot verification and basic setup
2. **Task Two**: Balance query implementation
3. **Task Three**: Transaction processing
4. **Task Four**: Kafka integration
5. **Task Five**: Advanced features and optimizations

## 🤝 Contributing

This is a educational project developed for the JPMC Advanced Software Engineering Forage program. Contributions follow the program guidelines and best practices.

## 📄 License

This project is part of the JPMC Forage program and is intended for educational purposes.

## 📞 Support

For questions related to this forage program, refer to the JPMC program documentation and support channels.

