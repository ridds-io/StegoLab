# StegoLab 🔒
### Multi-Medium Steganography Benchmarking Platform

> A Java-based platform to encode and decode hidden messages across multiple carrier mediums, with automated benchmarking of accuracy, speed, and memory usage.

---

## 📌 What is StegoLab?

Steganography is the science of hiding information *inside* something ordinary — an image, a piece of code, an ASCII drawing, or plain text — so that no one even suspects a secret exists. Unlike encryption (which scrambles data but signals something is hidden), steganography makes the secret **invisible**.

StegoLab is the first platform to:
- Let users **choose** from multiple steganographic algorithms
- Apply them across **multiple carrier mediums**
- **Benchmark** every run — accuracy %, encode time, decode time, memory used
- **Compare** results side by side to find the best algorithm-medium combination

---

## 🗂️ Supported Mediums & Algorithms

| Medium | Algorithm | Type |
|---|---|---|
| Image | LSB (Least Significant Bit) | Established |
| Image | DCT-based Encoding | Established |
| Source Code | Code Style Encoding | ⭐ Novel |
| ASCII Art | Character Substitution | ⭐ Novel |
| Plain Text | Zero-Width Character Encoding | ⭐ Novel |
| Plain Text | Synonym Substitution | ⭐ Novel |

---

## 🏗️ Project Structure

```
stegolab/
├── src/
│   └── main/
│       └── java/
│           └── com/stegolab/
│               ├── base/
│               │   └── StegoBase.java          # Abstract base class
│               ├── algorithms/
│               │   ├── StegoAlgorithm.java     # Algorithm interface
│               │   ├── LSBAlgorithm.java        # LSB implementation
│               │   ├── DCTAlgorithm.java        # DCT implementation
│               │   ├── CodeStyleAlgorithm.java  # Code style encoding
│               │   ├── CharSubAlgorithm.java    # ASCII char substitution
│               │   ├── ZeroWidthAlgorithm.java  # Zero-width text encoding
│               │   └── SynonymAlgorithm.java    # Synonym substitution
│               ├── medium/
│               │   ├── ImageStego.java          # Image carrier
│               │   ├── CodeStego.java           # Source code carrier
│               │   ├── AsciiStego.java          # ASCII art carrier
│               │   └── TextStego.java           # Plain text carrier
│               ├── benchmark/
│               │   ├── BenchmarkEngine.java     # Runs & measures operations
│               │   └── BenchmarkResult.java     # Stores benchmark metrics
│               ├── factory/
│               │   └── StegoFactory.java        # Creates correct stego instance
│               ├── registry/
│               │   └── AlgorithmRegistry.java   # Tracks available algorithms
│               └── app/
│                   └── StegoLabApp.java         # Entry point
├── README.md
└── pom.xml
```

---

## ⚙️ OOP Concepts Demonstrated

| Concept | Where Used |
|---|---|
| **Abstraction** | `StegoBase` abstract class — defines the contract all algorithms must follow |
| **Inheritance** | `ImageStego`, `CodeStego`, `AsciiStego`, `TextStego` all extend `StegoBase` |
| **Polymorphism** | `BenchmarkEngine` operates on `StegoBase` references — works with any medium at runtime |
| **Encapsulation** | Each medium class hides internal bit buffers, char maps, and encoding state privately |
| **Interfaces** | `StegoAlgorithm` separates *what* we hide in from *how* we hide it |
| **Strategy Pattern** | Algorithm is injected at runtime — swap LSB for DCT without changing medium class |
| **Factory Pattern** | `StegoFactory` centralises object creation based on user selection |
| **Open/Closed Principle** | New algorithms/mediums added as new classes — no existing code modified |

---

## 📊 Benchmarking Metrics

Every encode/decode run is automatically measured across:

- **Accuracy (%)** — character-level match between original and decoded message
- **Encode Time (ms)** — time to embed the message using `System.nanoTime()`
- **Decode Time (ms)** — time to extract and reconstruct the message
- **Memory Used (KB)** — heap delta via `Runtime.getRuntime()`

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 17+
- Maven 3.8+

### Run
```bash
git clone https://github.com/your-username/stegolab.git
cd stegolab
mvn compile
mvn exec:java -Dexec.mainClass="com.stegolab.app.StegoLabApp"
```

---

## 🧪 Example Usage (CLI)

```
Welcome to StegoLab
-------------------
Select Medium:
  1. Image
  2. Source Code
  3. ASCII Art
  4. Plain Text

Select Algorithm:
  1. LSB
  2. DCT

Enter carrier file path: cover_image.png
Enter secret message: Hello World

[Encoding...]
[Decoding...]

=== Benchmark Results ===
Algorithm     : LSB
Medium        : Image
Accuracy      : 100.00%
Encode Time   : 4 ms
Decode Time   : 3 ms
Memory Used   : 18 KB
```

---

## 📚 Phase Breakdown

| Phase | Deliverable | Status |
|---|---|---|
| Phase 1 | Problem Definition & OOP Design | ✅ Complete |
| Phase 2 | Core Implementation (encode/decode) | 🔄 In Progress |
| Phase 3 | Benchmarking & Comparison Engine | ⏳ Upcoming |
| Phase 4 | Testing & Final Demo | ⏳ Upcoming |

---

## 👥 Team

- [ridds-io](https://github.com/ridds-io) 
- [riya-tyagi18](https://github.com/riya-tyagi18)
- [sanwal-05](https://github.com/sanwal-05)
