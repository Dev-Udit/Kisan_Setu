# 🌾 Kisan Setu (किसान सेतु)

> **"बीज से बाज़ार तक, किसान के विकास का सेतु"**  
> *(From seed to market, the bridge to a farmer's development.)*

Kisan Setu is a comprehensive agricultural distribution and slot management system built with Spring Boot. It serves as a digital bridge to empower farmers by simplifying crop management, streamlining harvest sales, and regulating fertilizer (Urea/DAP) requests based on verified land area.

---

## ✨ Features

### 🧑‍🌾 For Farmers
* **Secure Registration & Login:** Robust authentication system with encrypted passwords using BCrypt.
* **Smart Slot Booking (Home-Based):** Book convenient time slots directly from home to sell crops or collect fertilizers, eliminating the need to wait in long, exhausting queues.
* **Crop Management:** Easily register and track crops grown on personal land.
* **Market Access:** Submit secure, scheduled requests to sell harvested crops at the right time.
* **Smart Fertilizer Allocation:** Request Urea and DAP fertilizers. The system automatically validates requests against the farmer's registered total land area (in acres) to prevent over-allocation.

### 🛡️ For Administrators
* **Crowd & Peak Season Management:** Regulate daily footfall at distribution centers and markets by managing slot capacities, effectively preventing chaos and stampedes during peak agricultural seasons.
* **Centralized Dashboard:** A powerful admin panel to oversee all agricultural activities and appointments.
* **Approval Workflows:** Review and approve/reject farmer requests for selling crops and acquiring fertilizers based on available inventory and time slots.
* **Mandi Intelligence:** Access vital market data to make informed distribution decisions.

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, HTML5, CSS3, Bootstrap 5
- **Database:** MySQL
- **Build Tool:** Maven

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 17+
- Maven
- MySQL Server

---

### Installation

1. **Clone the repository**

```bash
git clone https://github.com/your-username/kisan-setu.git
cd kisan-setu
```

2. **Configure Database**

Create a MySQL database:

```sql
CREATE DATABASE kisan_setu;
```

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kisan_setu
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the Application**

```bash
./mvnw spring-boot:run
```

---

## 🌐 Access the Application

- Home Page: http://localhost:8080/

## 📂 Project Structure

```
controller/   → Web requests and routing
service/      → Business logic & validations
repository/   → Database operations (JPA)
entity/       → Database models
config/       → Security configuration
templates/    → Thymeleaf UI pages
```

---

## 🔒 Security

- Spring Security authentication
- BCrypt password encryption
- Role-based access (Farmer/Admin)
- Request validation & authorization checks

---

## 👨‍💻 Developed By

**Udit Thapak**

---

## 📌 Future Enhancements

- Mobile app integration
- Real-time mandi price updates
- SMS alerts for farmers
- Fertilizer stock tracking
- Government API integration

---

## 📄 License

This project is developed for academic and learning purposes.

---

⭐ If you like this project, consider giving it a star on GitHub!
