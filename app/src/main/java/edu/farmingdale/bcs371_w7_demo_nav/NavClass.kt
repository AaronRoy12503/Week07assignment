package edu.farmingdale.bcs371_w7_demo_nav

import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SpalshScreen(navController)
        }

        composable("first_screen") {
            FirstScreen(navController)
        }

        composable("second_screen") {
            SecondScreen(navController)
        }

        // ToDo 7: Add more nav screens here for the pizza party and gpa calculator
        composable("pizza_party_screen") {
            PizzaPartyScreen(navController)
        }

        composable("gpa_calculator_screen") {
            GPACalculatorScreen(navController)
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "First Screen")

            Button(onClick = { navController.navigate("second_screen") }) {
                Text(text = "Go to Second Screen")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { navController.navigate("pizza_party_screen") }) {
                Text(text = "Go to Pizza Party")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { navController.navigate("gpa_calculator_screen") }) {
                Text(text = "Go to GPA Calculator")
            }
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    var sliderValue by remember { mutableStateOf(0.5f) }
    var isSwitchChecked by remember { mutableStateOf(true) } // For ToDo 8

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            enabled = isSwitchChecked, // ToDo 8: Disable slider when switch is off
            modifier = Modifier.fillMaxWidth()
        )

        Text(fontSize = 20.sp, text = "Second Screen")

        Button(onClick = { context.startActivity(Intent(context, MainActivity2::class.java)) }) {
            Text(fontSize = 20.sp, text = "Go to other Activity")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ToDo 8: When the switch is off, disable the slider
        Switch(
            checked = isSwitchChecked,
            onCheckedChange = { isSwitchChecked = it },
            modifier = Modifier.padding(10.dp)
        )
        Text(text = if (isSwitchChecked) "Slider is Enabled" else "Slider is Disabled")
    }
}

@Composable
fun PizzaPartyScreen(navController: NavController) {
    // Simple UI for the Pizza Party screen
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pizza Party Screen", fontSize = 24.sp)
        // Add your pizza party UI elements here

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back")
        }
    }
}

@Composable
fun GPACalculatorScreen(navController: NavController) {
    // Simple UI for the GPA Calculator screen
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "GPA Calculator Screen", fontSize = 24.sp)
        // Add your GPA calculator UI elements here

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back")
        }
    }
}

@Composable
fun SpalshScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
        delay(3000)
        navController.navigate("first_screen")
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fsclogo),
            contentDescription = "",
            modifier = Modifier.scale(scale.value)
        )
    }
}
