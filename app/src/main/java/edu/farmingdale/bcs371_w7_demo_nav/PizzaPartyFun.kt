package edu.farmingdale.bcs371_w7_demo_nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import kotlin.math.ceil

//@Composable
//fun Navigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "splash_screen") {
//        composable("splash_screen") {
//            SpalshScreen(navController)
//        }
//        composable("first_screen") {
//            FirstScreen(navController)
//        }
//        composable("second_screen") {
//            SecondScreen(navController)
//        }
//        composable("pizza_party_screen") {
//            PizzaPartyScreen(navController) // Made navigatable
//        }
//        composable("gpa_calculator_screen") {
//            GPACalculatorScreen(navController)
//        }
//        // Other destinations...
//    }
//}

// ToDo 9: make this composable navigatable and then add a button to navigate to the GPA calculator
@Composable
fun PizzaPartyScreen(navController: NavController, modifier: Modifier = Modifier) {
    var totalPizzas by remember { mutableIntStateOf(0) }
    var numPeopleInput by remember { mutableStateOf("") }
    var hungerLevel by remember { mutableStateOf("Medium") }

    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = "Pizza Party",
            fontSize = 38.sp,
            modifier = modifier.padding(bottom = 16.dp)
        )
        NumberField(
            labelText = "Number of people?",
            textInput = numPeopleInput,
            onValueChange = { numPeopleInput = it },
            modifier = modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        RadioGroup(
            labelText = "How hungry?",
            radioOptions = listOf("Light", "Medium", "Very hungry"),
            selectedOption = hungerLevel,
            onSelected = { hungerLevel = it },
            modifier = modifier
        )
        Text(
            text = "Total pizzas: $totalPizzas",
            fontSize = 22.sp,
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Button(
            onClick = {
                totalPizzas = calculateNumPizzas(
                    numPeopleInput.toIntOrNull() ?: 0,
                    hungerLevel
                )
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Added a button to navigate to the GPA calculator
        Button(
            onClick = { navController.navigate("gpa_calculator_screen") },
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Go to GPA Calculator")
        }
    }
}

@Composable
fun NumberField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    TextField(
        value = textInput,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

@Composable
fun RadioGroup(
    labelText: String,
    radioOptions: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }

    Column {
        Text(labelText)
        radioOptions.forEach { option ->
            Row(
                modifier = modifier
                    .selectable(
                        selected = isSelectedOption(option),
                        onClick = { onSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedOption(option),
                    onClick = null,
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = option,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

fun calculateNumPizzas(
    numPeople: Int,
    hungerLevel: String
): Int {
    val slicesPerPizza = 8
    val slicesPerPerson = when (hungerLevel) {
        "Light" -> 2
        "Medium" -> 3
        else -> 4
    }

    return ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
}

// Placeholder for GPA Calculator Screen
@Composable
fun GPACalculatorScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = "GPA Calculator",
            fontSize = 38.sp,
            modifier = modifier.padding(bottom = 16.dp)
        )
        // Add GPA calculator UI components here

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigateUp() },
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
