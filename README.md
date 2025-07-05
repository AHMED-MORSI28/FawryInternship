![operation1](https://github.com/user-attachments/assets/f6c9d09c-f0fc-42dc-a537-f41e91d077d7)
![operation7](https://github.com/user-attachments/assets/a319c052-dd83-4bf0-93a6-7292f4954b02)
![operation6](https://github.com/user-attachments/assets/66dec2d7-3276-4fec-9907-c545b0f60197)
![operation5](https://github.com/user-attachments/assets/2bf6bf05-ee17-4c19-86bd-5666620317f6)
![operation4](https://github.com/user-attachments/assets/e64bf8e9-f8b7-436a-a868-0708471fecb5)
![operation3](https://github.com/user-attachments/assets/827c6b46-0ca0-4c08-9c8b-948c0d05d3bd)
![operation2](https://github.com/user-attachments/assets/1bd7b749-abfd-45d2-a116-0266a24fbce3)
# 🛒 Console‑Shop: A Modern CLI E‑commerce System

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

A lean, clean Java implementation of e-commerce core logic without the bloat. Perfect for showcasing OOP principles, business rule implementation, and clean architecture in a compact codebase.

## 🚀 Features

- **Domain-Driven Design** with clear separation of concerns
- **Inventory Management** with real-time stock validation
- **Business Rules Engine** using Java enums for expiry and shipping logic
- **Shopping Cart System** with weight calculation and expiry date tracking
- **Accounting Logic** with balance management and receipt generation
- **CSV Data Persistence** with no external dependencies

## 📸 Application Screenshots

<details>
<summary>Click to expand screenshots</summary>

![Main Menu](https://github.com/user-attachments/assets/f6c9d09c-f0fc-42dc-a537-f41e91d077d7)
![Adding Items](https://github.com/user-attachments/assets/1bd7b749-abfd-45d2-a116-0266a24fbce3)
![View Cart](https://github.com/user-attachments/assets/827c6b46-0ca0-4c08-9c8b-948c0d05d3bd)
![Checkout Process](https://github.com/user-attachments/assets/e64bf8e9-f8b7-436a-a868-0708471fecb5)
![Receipt Generation](https://github.com/user-attachments/assets/2bf6bf05-ee17-4c19-86bd-5666620317f6)
![Account View](https://github.com/user-attachments/assets/66dec2d7-3276-4fec-9907-c545b0f60197)
![Exit Screen](https://github.com/user-attachments/assets/a319c052-dd83-4bf0-93a6-7292f4954b02)

</details>

## 🔧 Quick Start

```bash
# Clone the repo
git clone https://github.com/your-username/console-shop.git
cd console-shop

# Compile (no build tools required)
javac -d out $(find src -name "*.java")

# Run
java -cp out Main
```

> **Requirements:** Java 17+ recommended (Java 11+ compatible)

## 🧩 System Architecture

| Component | Purpose | Implementation |
|-----------|---------|----------------|
| `ProductLoader` | Data access layer | Parses CSV into domain objects |
| `Product` | Domain model | Encapsulates product attributes and business rules |
| `ShoppingCart` | Service layer | Manages cart state and validation logic |
| `CheckoutService` | Business logic | Handles expiration checks and shipping calculations |
| `ReceiptGenerator` | View layer | Formats transactional data for display |
| `CustomerAccount` | Domain model | Tracks financial state |

## 🔄 Workflow

1. **Catalog Loading** → CSV parsing with strong typing and validation
2. **Inventory Browsing** → Console-based product listing with availability
3. **Cart Management** → Real-time stock validation and constraint checking
4. **Checkout Process** → Business rule application (expiry filtering, shipping calculation)
5. **Receipt Generation** → Formatted output with line items, subtotals, and fees
6. **Account Updates** → Balance management with transaction history

## 📋 Product Catalog Specification

The `products.csv` file uses the following schema:

```
name,price,quantity,isExpire,requireShipping,weight,productionDate,expirationDays
```

### Field Specifications:

- **Enum Values** (case-sensitive):
  - `isExpire`: `mayExpire` | `mayNotExpire`
  - `requireShipping`: `needsShipping` | `doesntNeedShipping`
- **Weight**: Decimal values in kilograms (e.g., `0.2`)
- **Dates**: ISO format (`YYYY-MM-DD`)
- **Expiry**: Leave `productionDate` & `expirationDays` empty for non-perishables

### Example Entry:
```
Cheese,80,25,mayExpire,needsShipping,0.2,2025-06-01,60
```

## 🧠 Engineering Rationale

This project demonstrates how to implement core e-commerce functionality without external frameworks or databases. It's optimized for:

- **Fast onboarding** - Run in seconds with zero config
- **Rule-based constraints** - Using type-safe enums instead of magic strings
- **Clean error handling** - Domain-specific exceptions with clear messaging
- **State transitions** - Immutable objects with controlled mutations
- **I/O separation** - Data loading decoupled from business logic

## 🛣️ Roadmap

- [ ] **Data Persistence**: Write inventory changes back to CSV
- [ ] **Test Coverage**: Add JUnit tests for business rules
- [ ] **UX Improvements**: Table-formatted receipt output
- [ ] **Format Support**: Add JSON catalog option
- [ ] **CI/CD**: Add Gradle build script and GitHub Actions

## 📜 License

MIT — Use freely, contribute generously, credit accordingly.
