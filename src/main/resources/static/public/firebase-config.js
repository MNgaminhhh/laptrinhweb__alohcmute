import { initializeApp } from "https://www.gstatic.com/firebasejs/10.7.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.7.0/firebase-analytics.js";

const firebaseConfig = {
    apiKey: "AIzaSyAYTzE1F3gZiHU274TiSb5FV85VtCpRw9Q",
    authDomain: "alohcmute-71b8f.firebaseapp.com",
    projectId: "alohcmute-71b8f",
    storageBucket: "alohcmute-71b8f.appspot.com",
    messagingSenderId: "682697103488",
    appId: "1:682697103488:web:799a8dbf97c95ec5ea6e65",
    measurementId: "G-M4CGJ5VVKH"
};

const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
console.log("Firebase initialized successfully!");