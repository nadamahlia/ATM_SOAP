# ATM SOAP – Automated Teller Machine

A two-tier ATM application developed using Java, SOAP Web Services (JAX-WS), Maven and PostgreSQL.

Academic project developed as part of the Distributed Architecture (Architecture Répartie) module.

## Technologies

- Java 22
- Java Swing
- SOAP Web Services (JAX-WS)
- XML / WSDL
- PostgreSQL
- JDBC
- Maven

## Architecture

### Tier 1 – Client
Java Swing graphical interface providing:
- User authentication
- Deposit operations
- Withdrawal operations
- SOAP communication with the server

### Tier 2 – Server
SOAP Web Service responsible for:
- Authentication verification
- Deposit and withdrawal operations
- Balance calculation
- Database access through JDBC

## Features

- Login using card number and PIN
- Deposit money
- Withdraw money
- Calculate account balance
- Store transactions in PostgreSQL

## Project Structure

```text
ATM_SOAP_Simple/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   ├── Server/
│       │   │   ├── ATMInterface.java
│       │   │   ├── Conn.java
│       │   │   ├── Metier.java
│       │   │   └── Server.java
│       │   └── client/
│       │       ├── Client.java
│       │       ├── Login.java
│       │       ├── Deposit.java
│       │       ├── Withdrawl.java
│       │       └── Transactios.java
│       └── resources/
│           └── ASimulatorSystem/icons/
└── .gitignore
```

## Database

PostgreSQL database with:
- `login`: authentication information
- `bank`: banking transactions

Configure database credentials through environment variables.

## Running the Application

1. Start PostgreSQL.
2. Configure the database and environment variables.
3. Run `Server.java` to publish the SOAP service.
4. Run `Login.java` to launch the client.

WSDL endpoint:

`http://localhost:8080/ATMService?wsdl`

## Learning Objectives

- Understanding two-tier distributed architecture
- Implementing SOAP Web Services
- Understanding XML and WSDL
- Developing Java client-server applications
- Connecting Java to PostgreSQL using JDBC
- Managing database transactions

## Author

Nada Mahlia

Academic Project – Distributed Architecture Module.
ATM_SOAP_Simple (RMI -> SOAP / JAX-WS, Maven, JDK 22)
1) PostgreSQL demarre (voir Server/Conn.java ; tables : login, bank)
2) SERVEUR : clic droit sur Server.java > Run File   (WSDL : http://localhost:8080/ATMService?wsdl)
3) CLIENT  : clic droit sur Login.java  > Run File
Images : src/main/resources/ASimulatorSystem/icons/ (atm.jpg, logo.jpg) - a remplacer par les vraies.
