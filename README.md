# 🚀 Blog API - Professional RESTful Backend

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green?style=for-the-badge&logo=springboot)
![JWT](https://img.shields.io/badge/JWT-Auth-blue?style=for-the-badge&logo=jsonwebtokens)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

> A production-grade, enterprise-level Blog API built with Spring Boot, featuring JWT authentication, comprehensive CRUD operations, and advanced features like pagination and validation.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Security](#-security)
- [Testing with Postman](#-testing-with-postman)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

---

## ✨ Features

### Core Functionality
- 🔐 **JWT Authentication** - Stateless, token-based authentication system
- 👥 **User Management** - Registration, login, and user profiles
- 📝 **Blog Posts** - Full CRUD operations with search capabilities
- 💬 **Comments System** - Nested comments on posts
- 🏷️ **Categories** - Organize posts by categories
- 🔍 **Search** - Search posts by keywords in title/content

### Advanced Features
- 📄 **Pagination** - Efficient handling of large datasets
- ✅ **Input Validation** - Comprehensive request validation
- 🛡️ **Global Exception Handling** - Consistent error responses
- 🔒 **BCrypt Password Encryption** - Secure password storage
- 🎯 **Role-Based Access Control** - User authorization
- 📊 **Database Relationships** - Proper entity relationships with JPA

---

## 🛠️ Tech Stack

### Backend
- **Java 21** - Latest LTS version
- **Spring Boot 3.2** - Main framework
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database operations
- **Hibernate** - ORM framework
- **JWT (jjwt)** - Token generation & validation

### Database
- **H2 Database** - In-memory database for development
- Can easily migrate to **MySQL/PostgreSQL** for production

### Build Tools
- **Maven** - Dependency management
- **Lombok** - Reducing boilerplate code

---

## 🏗️ Architecture
```
blog-api/
│
├── src/main/java/com/blog/blogapi/
│   ├── controller/          # REST API endpoints
│   │   ├── AuthController
│   │   ├── PostController
│   │   ├── CategoryController
│   │   └── CommentController
│   │
│   ├── service/             # Business logic layer
│   │   ├── AuthService
│   │   ├── PostService
│   │   ├── CategoryService
│   │   ├── CommentService
│   │   └── CustomUserDetailsService
│   │
│   ├── repository/          # Data access layer
│   │   ├── UserRepository
│   │   ├── PostRepository
│   │   ├── CategoryRepository
│   │   └── CommentRepository
│   │
│   ├── entity/              # JPA entities
│   │   ├── User
│   │   ├── Post
│   │   ├── Category
│   │   └── Comment
│   │
│   ├── dto/                 # Data Transfer Objects
│   │   ├── RegisterRequest
│   │   ├── LoginRequest
│   │   ├── AuthResponse
│   │   ├── PostRequest
│   │   └── CommentRequest
│   │
│   ├── JwtUtil              # JWT token utilities
│   ├── JwtRequestFilter     # JWT authentication filter
│   ├── SecurityConfig       # Security configuration
│   └── GlobalExceptionHandler  # Exception handling
│
└── src/main/resources/
    └── application.properties  # App configuration
```

---

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- Git
- Postman (for API testing)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/blog-api-spring-boot.git
cd blog-api-spring-boot
```

2. **Build the project**
```bash
mvn clean install
```

3. **Run the application**
```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

4. **Access H2 Console** (Optional)
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:blogdb
Username: sa
Password: (leave empty)
```

---

## 📚 API Documentation

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "john",
  "email": "john@example.com",
  "role": "USER"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john",
  "password": "password123"
}
```

### Post Endpoints (Requires JWT Token)

#### Create Post
```http
POST /api/posts
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "title": "My First Blog Post",
  "content": "This is the content of my post",
  "categoryId": 1
}
```

#### Get All Posts (with Pagination)
```http
GET /api/posts?page=0&size=10
Authorization: Bearer {your-jwt-token}
```

#### Get Post by ID
```http
GET /api/posts/{id}
Authorization: Bearer {your-jwt-token}
```

#### Update Post
```http
PUT /api/posts/{id}
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "title": "Updated Title",
  "content": "Updated content",
  "categoryId": 1
}
```

#### Delete Post
```http
DELETE /api/posts/{id}
Authorization: Bearer {your-jwt-token}
```

#### Search Posts
```http
GET /api/posts/search?keyword=spring
Authorization: Bearer {your-jwt-token}
```

### Category Endpoints

#### Create Category
```http
POST /api/categories
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "name": "Technology",
  "description": "Tech related posts"
}
```

#### Get All Categories
```http
GET /api/categories
Authorization: Bearer {your-jwt-token}
```

### Comment Endpoints

#### Add Comment to Post
```http
POST /api/posts/{postId}/comments
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "content": "Great post!"
}
```

#### Get Comments for Post
```http
GET /api/posts/{postId}/comments
Authorization: Bearer {your-jwt-token}
```

#### Delete Comment
```http
DELETE /api/posts/{postId}/comments/{commentId}
Authorization: Bearer {your-jwt-token}
```

---

## 🗄️ Database Schema
```sql
Users
├── id (PK)
├── username (UNIQUE)
├── email (UNIQUE)
├── password (ENCRYPTED)
└── role

Posts
├── id (PK)
├── title
├── content
├── user_id (FK -> Users)
├── category_id (FK -> Categories)
├── created_at
└── updated_at

Categories
├── id (PK)
├── name (UNIQUE)
└── description

Comments
├── id (PK)
├── content
├── user_id (FK -> Users)
├── post_id (FK -> Posts)
└── created_at
```

---

## 🔐 Security

### JWT Authentication Flow
1. User registers/logs in → receives JWT token
2. Client stores token (localStorage/sessionStorage)
3. Client sends token in Authorization header for each request
4. Server validates token and extracts user information
5. Request proceeds if token is valid

### Password Security
- Passwords are encrypted using **BCrypt** algorithm
- Never stored in plain text
- Salt rounds: 10

### Token Expiration
- JWT tokens expire after **24 hours**
- Users must re-authenticate after expiration

---

## 🧪 Testing with Postman

### Setup
1. Import the API collection (if provided)
2. Set base URL: `http://localhost:8080`

### Workflow
1. **Register** a new user → Save the JWT token
2. **Login** with credentials → Get JWT token
3. Add token to **Authorization** tab (Bearer Token)
4. Test all protected endpoints with the token

### Sample Test Scenarios
- ✅ Register multiple users
- ✅ Create posts with different users
- ✅ Test pagination with different page sizes
- ✅ Validate input errors (empty fields, short passwords)
- ✅ Test unauthorized access (without token)
- ✅ Search functionality with various keywords

---

## 🔮 Future Enhancements

- [ ] **Likes & Reactions** - Like/unlike posts
- [ ] **Bookmarks** - Save favorite posts
- [ ] **User Profiles** - Extended user information
- [ ] **File Uploads** - Profile pictures and post images
- [ ] **Email Notifications** - Comment/like notifications
- [ ] **Social Login** - Google/GitHub OAuth
- [ ] **Rate Limiting** - API throttling
- [ ] **Caching** - Redis integration
- [ ] **Swagger/OpenAPI** - Interactive API documentation
- [ ] **Docker** - Containerization
- [ ] **CI/CD Pipeline** - Automated testing & deployment
- [ ] **PostgreSQL/MySQL** - Production database migration

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📧 Contact

**VISHNU C** - [vishnuc234567@gmail.com](mailto:vishnuc234567@gmail.com)

**GitHub** - [@Vishnu-C-10](https://github.com/ishnu-C-10)

**Project Link** - [https://github.com/Vishnu-C-10/blog-api-spring-boot](https://github.com/ishnu-C-10/blog-api-spring-boot)

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- JWT.io for JWT resources
- Baeldung for Spring Security tutorials
- Stack Overflow community

---

<div align="center">

**⭐ Star this repo if you found it helpful! ⭐**

Made with ❤️ and ☕

</div>
