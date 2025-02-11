# Obfuscation Example

This project demonstrates Java code obfuscation using ProGuard. It serves as a companion to my Medium article, where I discuss the concept, usage, benefits, and approaches of obfuscation in Java.

## 📚 Project Structure

```
obfuscation-example/
│── .idea/               # IDE configuration files (IntelliJ IDEA)
│── .mvn/wrapper/        # Maven Wrapper for easier build management
│── src/                 # Source code directory
│── target/              # Compiled output directory
│── proguard.conf        # ProGuard configuration file
│── pom.xml              # Maven project configuration
│── mvnw, mvnw.cmd       # Maven Wrapper scripts (Linux/Windows)
│── README.md            # Project documentation
│── HELP.md              # Additional help documentation
│── .gitignore           # Git ignore rules
│── .gitattributes       # Git attributes configuration
```

## 🛠️ Setup & Installation

To run this project and apply ProGuard for obfuscation, follow these steps:

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/italo-gouveia/obfuscation-example.git
cd obfuscation-example
```

### 2️⃣ Build the Project

Ensure you have Maven installed, then build the project:
```bash
./mvnw clean package
```

### 3️⃣ Apply ProGuard Obfuscation

Run ProGuard using the provided proguard.conf configuration:

```bash
java -jar path/to/proguard.jar @proguard.conf
```

This step will obfuscate the compiled Java bytecode, making it harder to reverse-engineer.

## 📝 Configuration (proguard.conf)

The proguard.conf file defines rules for obfuscation, including:

Class name shortening

Code optimization

Dead code removal

Keeping necessary public APIs

Modify the file as needed to fine-tune obfuscation levels for your project.

## 📚 Learn More

For a deeper understanding of Java obfuscation and its importance, read my full article:🔗 Obfuscating Code in Java

## 💜 License

This project is open-source and intended for educational purposes. Feel free to modify and experiment!
