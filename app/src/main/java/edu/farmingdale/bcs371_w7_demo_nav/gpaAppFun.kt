package edu.farmingdale.bcs371_w7_demo_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment

// ToDo 10: Make this composable navigable and then add a button to navigate to a suitable screen
@Composable
fun GPAAppScreen(navController: NavController) {

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }

    // Variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }
    var btnLabel by remember { mutableStateOf("Calculate GPA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = grade1,
            onValueChange = { grade1 = it },
            label = { Text("Course 1 Grade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = grade2,
            onValueChange = { grade2 = it },
            label = { Text("Course 2 Grade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = grade3,
            onValueChange = { grade3 = it },
            label = { Text("Course 3 Grade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (btnLabel == "Calculate GPA") {
                    val gpaVal = calGPA(grade1, grade2, grade3)
                    if (gpaVal != null) {
                        gpa = String.format("%.2f", gpaVal)

                        // Change background color based on GPA
                        backColor = when {
                            gpaVal < 60 -> Color.Red
                            gpaVal in 60.0..79.0 -> Color.Yellow
                            else -> Color.Green
                        }
                        btnLabel = "Clear"
                    } else {
                        gpa = "Invalid input"
                    }
                } else {
                    // Reset all values
                    grade1 = ""
                    grade2 = ""
                    grade3 = ""
                    gpa = ""
                    backColor = Color.White
                    btnLabel = "Calculate GPA"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(btnLabel)
        }

        if (gpa.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "GPA: $gpa",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to navigate to a suitable screen (e.g., back to the first screen)
        Button(
            onClick = { navController.navigate("first_screen") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Main Menu")
        }
    }
}

fun calGPA(grade1: String, grade2: String, grade3: String): Double? {
    return try {
        val grades = listOf(grade1, grade2, grade3).map { it.toDouble() }
        grades.average()
    } catch (e: NumberFormatException) {
        null // Return null if input is invalid
    }
}

