# 🚌 Smart Transit Kiosk Terminal

An interactive, Java Swing-based desktop application simulating a **Smart Public Transit Kiosk System** for managing bus and metro rail ticketing, route visualization, live status marquee, and digital bKash payments.

![GitHub repo size](https://img.shields.io/github/repo-size/CodewithShahriar/Smart-City-Transit-Rapid-Ticket-System.git)
![GitHub license](https://img.shields.io/github/license/CodewithShahriar/Smart-City-Transit-Rapid-Ticket-System.git)
![Java Version](https://img.shields.io/badge/Java-11%2B-orange)

---

## 📸 Application Previews

<div align="center">
  
  <h3>Main Application Interface</h3>
  <img src="previews/main_ui.png" alt="Main Dashboard UI" width="850"/>
  
  <br/><br/>
  
  <table border="0">
    <tr>
      <td align="center">
        <b>bKash Payment</b><br/>
        <img src="previews/route_map.png" alt="Route Map" width="400"/>
      </td>
      <td align="center">
        <b>Receipt</b><br/>
        <img src="previews/payment_ticket.png" alt="Payment Modal & Ticket" width="400"/>
      </td>
    </tr>
  </table>
</div>

---

## ✨ Key Features

- **🎯 Smart Nearest-5 Fare Rounding:** Fare amounts are automatically rounded to the nearest multiple of 5 (e.g., ৳15, ৳25, ৳50, ৳65) for seamless cash/digital transactions.
- **🗺️ Interactive Route Map:** Custom-drawn octilinear transit map dynamically highlighting routes, distances, and station fares based on origin and destination.
- **📟 5x7 Dot-Matrix LED Marquee:** Real-time scrolling LED banner displaying live bus schedules and announcements.
- **💺 Real-time Seat Availability Tracker:** Dynamic badge and progress bar showing available vs. total seats for selected transit operators.
- **📱 bKash Merchant Payment Gateway:** Integrated QR Code payment modal for simulating quick digital ticket purchases.
- **🎟️ Printable Digital Ticket & Barcode:** Generates a clean, formatted receipt complete with an auto-generated 1D barcode and unique transaction reference.

---

## 🛠️ Tech Stack & Concepts Used

- **Language:** Java (JDK 11 or higher)
- **GUI Framework:** Java Swing, AWT (Custom Graphics2D, Path2D, Double Buffering)
- **Design Concepts:** Object-Oriented Programming (Polymorphism, Inheritance, Encapsulation)
- **External Image API:** `javax.imageio.ImageIO` with dynamic URL image rendering

---

## 🚀 Getting Started

### Prerequisites
Make sure you have **Java Development Kit (JDK) 11** or higher installed on your system.

```bash
java -version

```

### Installation & Execution

1. **Clone the repository:**
```bash
git clone [https://github.com/YOUR_GITHUB_USERNAME/YOUR_REPO_NAME.git](https://github.com/YOUR_GITHUB_USERNAME/YOUR_REPO_NAME.git)
cd YOUR_REPO_NAME

```


2. **Compile the Java file:**
```bash
javac SmartTransportApp.java

```


3. **Run the application:**
```bash
java SmartTransportApp

```



---

## 📂 How to Add Your Preview Images

1. Create a folder named `previews` in your project root repository.
2. Put your screenshot images inside the `previews/` folder with the following names (or change the names inside `README.md`):
* `main_ui.png` (Main App Screen)
* `route_map.png` (Route Map Diagram)
* `payment_ticket.png` (Payment Modal / Printed Ticket)


3. Commit and push the images to GitHub!

---

## 📄 License

This project is open-source and available under the [MIT License](https://www.google.com/search?q=LICENSE).