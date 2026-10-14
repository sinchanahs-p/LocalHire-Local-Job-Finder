package com.example.localhire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocalHireApp()
        }
    }
}

@Composable
fun LocalHireApp() {

    var screen by remember { mutableStateOf("welcome") }

    MaterialTheme {

        when (screen) {

            "welcome" -> WelcomeScreen(
                onLogin = { screen = "login" },
                onCreateAccount = { screen = "register" }
            )

            "login" -> LoginScreen(
                onLoginSuccess = { screen = "home" },
                onCreateAccount = { screen = "register" },
                onBack = { screen = "welcome" }
            )

            "register" -> RegisterScreen(
                onAccountCreated = { screen = "home" },
                onBack = { screen = "welcome" }
            )

            "home" -> HomeScreen(
                onFindJob = { screen = "jobs" },
                onHireWorker = { screen = "workers" },
                onLogout = { screen = "welcome" }
            )

            "jobs" -> JobsScreen(
                onBack = { screen = "home" }
            )

            "workers" -> WorkersScreen(
                onBack = { screen = "home" }
            )
        }
    }
}


/* ---------------- WELCOME SCREEN ---------------- */

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
    onCreateAccount: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 247, 255))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(90.dp))

        Text(
            text = "LocalHire",
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = Color(63, 81, 181)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Connect locally. Work locally.",
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Find trusted local jobs and skilled workers near you.",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(70.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedButton(
            onClick = onCreateAccount,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Create Account",
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Find • Connect • Work",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}


/* ---------------- LOGIN SCREEN ---------------- */

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onCreateAccount: () -> Unit,
    onBack: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 247, 255))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(55.dp))

        Text(
            text = "Welcome Back!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(63, 81, 181)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Login to continue to LocalHire",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(35.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))
        Button(
                onClick = onLoginSuccess,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(15.dp)
        ) {
        Text("Login", fontSize = 17.sp)
    }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(onClick = onCreateAccount) {
            Text("Don't have an account? Create Account")
        }

        TextButton(onClick = onBack) {
            Text("← Back")
        }
    }
}


/* ---------------- CREATE ACCOUNT ---------------- */

@Composable
fun RegisterScreen(
    onAccountCreated: () -> Unit,
    onBack: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var role by remember { mutableStateOf("Job Seeker") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 247, 255))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Create Account",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(63, 81, 181)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Join the LocalHire community",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                placeholder = { Text("Enter your full name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                placeholder = { Text("Enter phone number") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                placeholder = { Text("City / Area") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "I am a:",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(
                        selected = role == "Job Seeker",
                        onClick = { role = "Job Seeker" }
                    )

                    Text("Job Seeker")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(
                        selected = role == "Employer",
                        onClick = { role = "Employer" }
                    )

                    Text("Employer")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = onAccountCreated,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Create Account",
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = onBack) {
                Text("← Back")
            }

            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}


/* ---------------- HOME SCREEN ---------------- */

@Composable
fun HomeScreen(
    onFindJob: () -> Unit,
    onHireWorker: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 247, 255))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Hello 👋",
            fontSize = 20.sp,
            color = Color.Gray
        )

        Text(
            text = "Welcome to LocalHire",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(63, 81, 181)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(63, 81, 181)
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Your local opportunities are waiting!",
                    color = Color.White,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Find work or connect with skilled workers near you.",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "What are you looking for?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onFindJob,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "💼  Find a Job",
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onHireWorker,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "👷  Hire a Worker",
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Popular Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("🔧 Electrician     🧹 Cleaner")

        Spacer(modifier = Modifier.height(8.dp))

        Text("🚗 Driver          💻 Computer Service")

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = onLogout,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Logout")
        }
    }
}


/* ---------------- JOB SCREEN ---------------- */

@Composable
fun JobsScreen(
    onBack: () -> Unit
) {

    val jobs = listOf(
        "Electrician Required" to "₹800 - ₹1500 / day",
        "Delivery Partner" to "₹12000 - ₹18000 / month",
        "Computer Technician" to "₹1000 / day",
        "House Cleaning" to "₹500 - ₹800 / day"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 247, 255))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Find a Job",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(63, 81, 181)
        )

        Text(
            text = "Available opportunities near you",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(jobs) { job ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = job.first,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("📍 Nearby")

                        Text(
                            text = "💰 ${job.second}",
                            color = Color(63, 81, 181),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Apply Now")
                        }
                    }
                }
            }

            item {

                TextButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("← Back to Home")
                }
            }
        }
    }
}


/* ---------------- WORKER SCREEN ---------------- */

@Composable
fun WorkersScreen(
    onBack: () -> Unit
) {

    val workers = listOf(
        Triple("Rahul Kumar", "Electrician", "4.8 ⭐"),
        Triple("Priya Sharma", "House Cleaner", "4.7 ⭐"),
        Triple("Arun Kumar", "Computer Technician", "4.9 ⭐"),
        Triple("Manoj", "Driver", "4.6 ⭐")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 247, 255))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Hire a Worker",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(63, 81, 181)
        )

        Text(
            text = "Skilled workers available near you",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(workers) { worker ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = worker.first,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text("🛠 ${worker.second}")

                        Text("📍 Available nearby")

                        Text(
                            text = worker.third,
                            color = Color(63, 81, 181),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Hire Worker")
                        }
                    }
                }
            }

            item {

                TextButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("← Back to Home")
                }
            }
        }
    }
}
