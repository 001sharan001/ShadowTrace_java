# 🔍 SHADOWTRACE – Detective Mystery Solving System

A Java-based console application that allows users to investigate mystery cases, manage suspects and clues, and solve mysteries by submitting their answers.

## 📌 Project Overview

**SHADOWTRACE** is a detective mystery-solving system developed using Java and MySQL. It provides an interactive console-based environment where registered detectives can explore mystery cases, examine suspects, review clues, and submit solutions.

The system evaluates submitted answers and automatically updates a case's status to `SOLVED` when the correct answer is submitted.

## ✨ Key Features

- **User Registration:** Create a detective account with username, email, and password.
- **Secure Login:** Authenticate users using password hashing with PBKDF2WithHmacSHA256.
- **Case Management:** Create new mystery cases, view available cases, view case details, and update case status.
- **Suspect Management:** Add and view suspects associated with mystery cases.
- **Clue Management:** Add and view clues associated with specific cases.
- **Solution Management:** Submit answers and view previously submitted solutions.
- **Automatic Case Evaluation:** Correct answers automatically update the case status to `SOLVED`.
- **MySQL Database Integration:** Store and retrieve application data using JDBC.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Core application development |
| JDBC | Database connectivity |
| MySQL 8.0 | Database management |
| Maven | Dependency management and build |
| VS Code | Development environment |
| MySQL Workbench | Database management |

## 🏗️ Project Structure

```text
SHADOWTRACE-CONSOLE/
│
├── .github/
├── .mvn/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── shadowtrace/
│                   ├── Main.java
│                   ├── dao/
│                   ├── database/
│                   ├── model/
│                   ├── service/
│                   └── util/
│
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

### Package Description

- **Main:** Application entry point and console menu.
- **DAO:** Handles database operations.
- **Database:** Manages MySQL connectivity.
- **Model:** Contains application data classes.
- **Service:** Implements application logic and validations.
- **Util:** Contains password hashing utilities.

## 🗄️ Database Design

The application uses a MySQL database named `shadowtrace_db`.

It contains five main tables:

1. `users` – Stores registered detective accounts.
2. `cases` – Stores mystery case information.
3. `suspects` – Stores suspects associated with cases.
4. `clues` – Stores clues associated with cases.
5. `solutions` – Stores submitted answers and evaluation results.

## ⚙️ Installation and Setup

### Prerequisites

Install the following:

- JDK 21
- MySQL Server 8.0
- MySQL Workbench 8.0
- Visual Studio Code

### 1. Clone the Repository

```bash
git clone https://github.com/001sharan001/ShadowTrace_java.git
```

### 2. Open the Project

Open the cloned `ShadowTrace_java` folder in VS Code.

### 3. Create the Database

Open MySQL Workbench and execute:

```sql
CREATE DATABASE IF NOT EXISTS shadowtrace_db;
```

Import the provided database backup if available. The additional case SQL script is located at:

```text
src/main/java/com/shadowtrace/shadowtrace_add_4_cases.sql
```

Execute this script in MySQL Workbench to add the four additional mystery cases.

### 4. Configure Database Connection

Open:

```text
src/main/java/com/shadowtrace/database/DatabaseConnection.java
```

Update the database credentials for your local MySQL installation:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/shadowtrace_db";

private static final String USER = "root";

private static final String PASSWORD =
        "ENTER_YOUR_MYSQL_PASSWORD";
```

Replace the placeholder with your own local MySQL password.

**Important:** Never upload your actual database password or other credentials to GitHub.

### 5. Compile the Project

Open the VS Code terminal in the project root and execute:

```powershell
.\mvnw.cmd clean compile
```

### 6. Generate the Classpath

```powershell
.\mvnw.cmd dependency:build-classpath "-Dmdep.outputFile=classpath.txt"
```

### 7. Run the Application

```powershell
java -cp "target\classes;$(Get-Content classpath.txt)" com.shadowtrace.Main
```

The SHADOWTRACE console menu will appear.

## 🔐 Security

- User passwords are hashed using `PBKDF2WithHmacSHA256`.
- A random salt is generated for password hashing.
- Password verification is performed against the stored hash.
- PreparedStatement is used for database queries.

## 🧪 Testing

The application has been tested for:

- User registration and login.
- Password
