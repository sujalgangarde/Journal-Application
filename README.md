# JournalApp 📝

A secure personal journaling web application built using Spring Boot and MongoDB. This project supports user registration, authentication via JWT, and the creation and management of personal journal entries.

---

## 🚀 Features

- ✅ User Registration & Login
- 🔐 JWT-based Authentication with Spring Security
- 📓 Create, Read, & Delete Journal Entries
- 🧠 MongoDB integration for document storage
- 🛡 Role-based access control
- 🛠 REST API endpoints for easy integration
- ⚙️ Clean architecture using service and repository layers
- 📦 Built using Maven, with Lombok and ModelMapper for efficiency

---

## 🧾 Tech Stack

| Layer          | Technology             |
| -------------- | ---------------------- |
| Backend        | Java + Spring Boot     |
| Database       | MongoDB                |
| Security       | Spring Security + JWT  |
| ORM            | Spring Data MongoDB    |
| Build Tool     | Maven                  |
| Others         | Lombok, ModelMapper    |

---

## 🗂️ Project Structure

```
journalApp/
├── src/
│   ├── main/
│   │   ├── java/com/sujalgangarde/journalApp/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── JournalApplication.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
```

---

## 🔑 Authentication

This application uses **JSON Web Tokens (JWT)** for secure, stateless authentication.
- On a successful login, a JWT is issued to the client.
- This token must be included in the `Authorization` header as a Bearer Token to access protected routes.
- **Example:** `Authorization: Bearer <your_jwt_token>`

---

## 📬 API Endpoints

### 🔐 Auth Routes
| Method | Endpoint             | Description             |
| :---   | :---                 | :---                    |
| `POST` | `/api/auth/register` | Registers a new user.   |
| `POST` | `/api/auth/login`    | Logs in a user and returns a JWT. |

### 📓 Journal Routes
| Method | Endpoint              | Description                             |
| :---   | :---                  | :---                                    |
| `GET`  | `/api/journal`        | Get all journals for the logged-in user. |
| `POST` | `/api/journal`        | Create a new journal entry.             |
| `DELETE`| `/api/journal/{id}`  | Delete a specific journal by its ID.      |

**Note:** All `/api/journal` routes are protected and require a valid JWT token.

---

## 🛠️ Setup Instructions

### Prerequisites
- Java 17+
- Apache Maven
- A running instance of MongoDB (local or cloud)

### Clone and Build
```bash
# Clone the repository
git clone https://github.com/sujalgangarde/Journal-Application.git

# Navigate to the project directory
cd Journal-Application

# Build the project using Maven
mvn clean install
```

### Run the Application

```bash
# Run the Spring Boot application
mvn spring-boot:run
```

The backend server will start at `http://localhost:8080`.

-----

## ⚙️ Configuration

You can configure the application by modifying the `src/main/resources/application.properties` file:

```properties
# Server Port
server.port=8080

# MongoDB Connection URI
spring.data.mongodb.uri=mongodb://localhost:27017/Journal-Application

# JWT Secret Key (change this to a long, random string)
jwt.secret=YourSecretKeyHere
```

-----

## 🔮 Future Improvements

  - [ ] Add a frontend client (React, Vue, or Angular)
  - [ ] Implement an "update" feature for journal entries
  - [ ] Add a rich text editor for more expressive journaling
  - [ ] Implement password reset via email
  - [ ] Add search and filter functionality (by date, tags, etc.)

-----

## 🤝 Contribution

Contributions are welcome!

1.  Fork the repository.
2.  Create your feature branch (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.

-----

## 📜 License

This project is licensed under the **MIT License**.

-----

## 🙋 Author

Developed by **Sujal Gangarde**

  - **GitHub:** [@sujalgangarde](https://github.com/sujalgangarde)
