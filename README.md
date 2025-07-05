![operation1](https://github.com/user-attachments/assets/f6c9d09c-f0fc-42dc-a537-f41e91d077d7)
![operation7](https://github.com/user-attachments/assets/a319c052-dd83-4bf0-93a6-7292f4954b02)
![operation6](https://github.com/user-attachments/assets/66dec2d7-3276-4fec-9907-c545b0f60197)
![operation5](https://github.com/user-attachments/assets/2bf6bf05-ee17-4c19-86bd-5666620317f6)
![operation4](https://github.com/user-attachments/assets/e64bf8e9-f8b7-436a-a868-0708471fecb5)
![operation3](https://github.com/user-attachments/assets/827c6b46-0ca0-4c08-9c8b-948c0d05d3bd)
![operation2](https://github.com/user-attachments/assets/1bd7b749-abfd-45d2-a116-0266a24fbce3)
# Console‑Shop – a tiny CLI e‑commerce demo

A bite‑sized Java project that mimics a checkout flow you might find in a real‑world store—just without the databases, web servers, or corporate overhead.

You get:

* **Catalog** loaded from `products.csv`
* **Shopping cart** that tracks stock, expiry dates, and shipping weight
* A “good enough for now” **receipt** printed to the terminal
* **Customer account** with running balance

Perfect for practicing Java collections, file I/O, and enum‑powered business rules.

---

## Quick start

```bash
git clone https://github.com/your‑username/console‑shop.git
cd console‑shop
javac -d out $(find src -name "*.java")   # compile
java  -cp out Main                        # run
```

> **Java 17+** recommended (anything 11+ should work).

---

## How it works

| Step                | What happens                                                                                                                             |
| ------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| **1. Load catalog** | `ProductLoader` reads `products.csv` – each row becomes a `Product` object.                                                              |
| **2. Browse**       | App shows inventory with price, stock, shipping flag, expiry flag.                                                                       |
| **3. Add to cart**  | Quantity is checked against stock immediately; no over‑selling allowed.                                                                  |
| **4. Checkout**     | Expired items are removed (notice shown). Shipping fee = `30 + (totalKg × 50)`.                                                          |
| **5. Receipt**      | Lists each shippable line with `qty × name (weight kg) = lineTotal`, subtotal, total weight, shipping, grand total, and updated balance. |

---

## Editing the catalog

`products.csv` columns (order matters):

```
name,price,quantity,isExpire,requireShipping,weight,productionDate,expirationDays
```

* **Enums are case‑sensitive**

  * `isExpire`: `mayExpire` | `mayNotExpire`
  * `requireShipping`: `needsShipping` | `doesntNeedShipping`
* **weight** in kilograms (e.g. `0.2`, **not** `200`)
* Leave `productionDate` & `expirationDays` blank for non‑perishables.

Example row:

```
Cheese,80,25,mayExpire,needsShipping,0.2,2025-06-01,60
```

---

## Menu cheat‑sheet

```
1. View cart      – see what’s in your basket
2. Add item       – pick item number & quantity
3. Checkout       – prints receipt, updates balance
4. View account   – shows customer name + current balance
5. Exit           – quits the program
```

---

## Why I built this

I wanted a lightweight, **offline** sandbox for a technical assignment that covered:

* CSV ingestion
* Enum‑driven rules (expiry, shipping)
* Basic state mutations (stock, balance)
* Clear error handling (out‑of‑stock, expired, empty cart)

Instead of spinning up Spring Boot and a database, I kept it terminal‑only so anyone can clone and run in under a minute.

---

## Roadmap / nice‑to‑haves

* Persist stock back to CSV after purchase
* Add JUnit tests for each rule branch
* Pretty‑print receipt as a table
* JSON catalog option
* Gradle build script

Pull requests welcome 🙂

---

## License

MIT — do whatever you like, just don’t hold me liable if you ship ten tons of cheese by mistake.
