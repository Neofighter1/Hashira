# Shamir's Secret Sharing - Java Implementation

**Student Assignment Project**  
**Hashira Placements Assignment - Catalog**

---

## 📌 Assignment Details

- **Assignment:** Hashira Placements Assignment - Online
- **Duration:** 45 minutes
- **Language:** Java (as required - Python not allowed)
- **Student:** Satya
- **Date:** October 29, 2025

---

## 📋 Problem Description

Given a set of points (roots) encoded in different bases, find the secret which is the constant term (c) of a polynomial.

- **n**: Total number of roots provided
- **k**: Minimum number of roots required to solve the polynomial
- **Polynomial degree**: k - 1

---

## 🚀 Quick Start

### Prerequisites
- Java JDK 8 or higher
- Any text editor or IDE

### Compilation
```bash
javac ShamirSecretInput.java
```

### Usage
```bash
java ShamirSecretInput <json-file>
```

### Examples
```bash
# Test Case 1
java ShamirSecretInput testcase1.json

# Test Case 2
java ShamirSecretInput testcase2.json
```

---

## 📝 Input Format

Create a JSON file with the following structure:

```json
{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}
```

### JSON Structure:
- **keys**: Contains `n` (total roots) and `k` (minimum required)
- **Point entries**: Numbered as "1", "2", "3", etc.
  - **base**: The base of the encoded value (string, base 2-36)
  - **value**: The encoded value (string)

---

## 📊 Test Cases

### Test Case 1
**Input:** `testcase1.json`
- n = 4, k = 3
- Points with bases: 10, 2, 10, 4

**Output:**
```
*** SECRET (Constant term c): 3 ***
```

### Test Case 2
**Input:** `testcase2.json`
- n = 10, k = 7
- Points with various bases (3, 6, 7, 8, 12, 15, 16)
- Handles very large numbers (40+ digits)

**Output:**
```
*** SECRET (Constant term c): -6290016743746469796 ***
```

---

## 🔧 How It Works

### 1. **Base Conversion**
Converts values from any base (2-36) to decimal using `BigInteger`:
```
Example: "111" in base 2 → 1×2² + 1×2¹ + 1×2⁰ = 7
```

### 2. **Lagrange Interpolation**
For k points (x₁, y₁), (x₂, y₂), ..., (xₖ, yₖ), the polynomial is:

```
P(x) = Σ yᵢ × Lᵢ(x)
```

where the Lagrange basis polynomial is:

```
Lᵢ(x) = Π[(x - xⱼ)/(xᵢ - xⱼ)] for all j ≠ i
```

### 3. **Finding the Secret**
The secret is found by evaluating P(0), which gives the constant term.

---

## ✨ Features

✅ **BigInteger Support** - Handles arbitrarily large numbers  
✅ **Multiple Bases** - Supports bases 2 through 36  
✅ **High Precision** - 50 decimal places for calculations  
✅ **JSON Input** - Easy to provide custom test cases  
✅ **Clear Output** - Shows all decoded values and steps  

---

## 📂 Project Structure

```
.
├── ShamirSecretInput.java    # Main program (reads JSON input)
├── testcase1.json             # Sample test case 1
├── testcase2.json             # Sample test case 2
└── README.md                  # This file
```

---

## 💡 Example Usage

### Create your own test case:

**mytest.json:**
```json
{
    "keys": {
        "n": 3,
        "k": 2
    },
    "1": {
        "base": "10",
        "value": "5"
    },
    "2": {
        "base": "16",
        "value": "a"
    },
    "3": {
        "base": "8",
        "value": "17"
    }
}
```

**Run:**
```bash
java ShamirSecretInput mytest.json
```

---

## 🎓 Algorithm Explanation

**Shamir's Secret Sharing** is a cryptographic algorithm where:
- A secret is divided into n parts
- Any k parts can reconstruct the secret
- Fewer than k parts reveal no information

The algorithm uses polynomial interpolation:
- A polynomial of degree (k-1) requires k points to be uniquely determined
- The secret is encoded as the constant term of the polynomial
- Given k points, we can reconstruct the polynomial and extract the secret

---

## 🔍 Sample Output

```
Processing: testcase1.json
==================================================
n (total roots): 4
k (minimum required roots): 3
Polynomial degree: 2

Point 1: base=10, encoded=4, decoded=4
Point 2: base=2, encoded=111, decoded=7
Point 3: base=10, encoded=12, decoded=12

Using first 3 points for interpolation

*** SECRET (Constant term c): 3 ***
```

---

## 🛠️ Technical Details

- **Language:** Java
- **Libraries:** Standard Java library only (no external dependencies)
- **Key Classes:**
  - `BigInteger` - For handling large numbers
  - `BigDecimal` - For high-precision calculations
  - Regex patterns - For JSON parsing

---

## 📝 Notes

- The program uses the first k points for interpolation
- All calculations are done with 50 decimal place precision
- Supports bases from 2 to 36 (0-9, a-z)
- Results are rounded to the nearest integer

---

## 🎓 Learning Outcomes

Through this assignment, I learned:
- Implementation of Shamir's Secret Sharing algorithm
- Working with BigInteger for handling large numbers
- Lagrange interpolation technique
- JSON parsing without external libraries
- Base conversion algorithms
- Polynomial reconstruction from points

---

## 📝 Assignment Notes

- **Input Format:** JSON files with encoded roots in various bases
- **Output:** The secret (constant term of polynomial)
- **Key Challenge:** Handling very large numbers (40+ digits)
- **Solution:** Used BigInteger and BigDecimal for precision

---

## 👨‍💻 Author

**Student Project**  
Created by: Satya  
Assignment: Hashira Placements Assignment - Online  
Duration: 45 minutes  
Date: October 29, 2025

This is a student submission for the Catalog Placements Assignment demonstrating implementation of Shamir's Secret Sharing algorithm in Java.

---

## 📄 License

This is a student assignment project for educational purposes.

---

## 🎯 Quick Reference

| Command | Description |
|---------|-------------|
| `javac ShamirSecretInput.java` | Compile the program |
| `java ShamirSecretInput testcase1.json` | Run test case 1 |
| `java ShamirSecretInput testcase2.json` | Run test case 2 |
| `java ShamirSecretInput myfile.json` | Run custom test case |

---

**Ready to use! Just provide your JSON test case and get the secret!** 🚀
