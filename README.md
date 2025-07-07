# 🌐 AWS Language Translator

A full-stack language translation application using **React (frontend)** and **AWS Lambda with Java SDK (backend)**. This app supports real-time text translation powered by **Amazon Translate**.

---

## 📁 Project Structure

AWS-Language-Translator/
├── Frontend/ # React-based UI
│ ├── index.html
│ ├── package.json
│ └── ...
└── TranslationBot/ 

# AWS Lambda Java backend
├── src/
├── pom.xml
└── ...



---

## 🚀 Features

- 🌍 Translate between multiple languages using Amazon Translate
- 🎙️ Speech-to-text and text-to-speech (via browser APIs)
- 📋 Copy-to-clipboard, audio playback, and text effects
- ⚙️ Java-based Lambda backend integrated with AWS SDK
- 📡 Secure, serverless, and scalable architecture

---

## 🔧 Technologies Used

### Frontend:
- React + Vite
- Tailwind CSS
- Framer Motion
- Text-to-Speech & Speech Recognition APIs
- Axios

### Backend:
- Java 11
- AWS Lambda (Amazon Translate integration)
- AWS SDK for Java

---

## 🚚 Setup Instructions

### 🔨 Frontend

```bash
cd Frontend
npm install
npm run dev


☁️ Backend (AWS Lambda with Java)
Navigate to TranslationBot

Use Maven or IntelliJ to build the project:

```bash
mvn clean package

Deploy the .jar to AWS Lambda:

Set handler in Lambda to: com.example.lambdafunction.YourHandlerClass::handleRequest

Ensure AmazonTranslateFullAccess permissions are set 
