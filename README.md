# Fawry Ecommerce Challenge

A simple Java-based e-commerce simulation system developed as part of the Fawry Quantum Internship Challenge.

---

## 📌 Project Overview

This project simulates a small e-commerce environment where:
- Products may have an expiry date.
- Some products require shipping.
- Customers can add products to a cart.
- Checkout validates all conditions, applies shipping, and generates a receipt.

---

## 📦 Features

✅ Define products with:
- Name
- Price
- Quantity
- (Optional) Expiry Date
- (Optional) Shipping Weight

✅ Product Types:
- **Cheese**, **Biscuits** → Expirable & Shippable
- **TV** → Non-expirable & Shippable
- **Scratch Card** → Non-expirable & Non-shippable

✅ Core Functionalities:
- Add products to cart with validation
- Validate expired/out-of-stock products
- Calculate subtotal, shipping, total amount
- Deduct from customer balance
- Print console receipt and shipping details
- Handle all error cases

---

## 🧪 Test Scenarios in `Main.java`

- ✅ Successful checkout with mixed products
- ❌ Empty cart error
- ❌ Expired product error
- ❌ Out-of-stock error
- ❌ Insufficient balance error
- ✅ Checkout with digital products only

---


## 🧠 Technologies Used

- Java 8+
- Object-Oriented Programming (OOP)
- `LocalDate` from `java.time` package
- Interfaces & Abstraction
- Console-based testing

---

## 🚀 How to Run

1. Clone or download the repo.
2. Open the project in IntelliJ or any Java IDE.
3. Run `Main.java`.
4. View output in the console.

---

## 👤 Author

Made with ❤️ by Mustafa Salem  
For the Fawry Quantum Internship Challenge 2025.
