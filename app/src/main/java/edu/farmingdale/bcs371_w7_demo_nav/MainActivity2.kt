package edu.farmingdale.bcs371_w7_demo_nav

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.farmingdale.bcs371_w7_demo_nav.ui.theme.BCS371_W7_Demo_NavTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BCS371_W7_Demo_NavTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BasicOperations(
                        name = "Activity 1",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BasicOperations(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var switchChecked by remember { mutableStateOf(true) } // ToDo 5: Fix the switch

    Column {
        Spacer(modifier = Modifier.padding(50.dp))
        Button(
            onClick = {
                val newInt = Intent(Intent.ACTION_VIEW)
                newInt.setData(Uri.parse("geo:0,0?q=Farmingdale State College, NY"))
                context.startActivity(newInt)
            },
            enabled = switchChecked, // ToDo 6: Disable buttons when switch is off
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location")
            Spacer(modifier = Modifier.width(10.dp)) // ToDo 3: Change spacing between icon and text
            Text("Show me Farmingdale")
        }
        HorizontalDivider(thickness = DividerDefaults.Thickness) // ToDo 4: Add horizontal divider

        Button(
            onClick = {
                // ToDo 1: Create implicit intent to call a phone number
                val newInt = Intent(Intent.ACTION_DIAL)
                newInt.data = Uri.parse("tel:1234567890") // Replace with actual number
                context.startActivity(newInt)
            },
            enabled = switchChecked, // ToDo 6: Disable buttons when switch is off
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        ) {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
            Spacer(modifier = Modifier.width(10.dp)) // ToDo 3: Change spacing between icon and text
            Text("Call Me")
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness) // ToDo 4: Add horizontal divider

        Button(
            onClick = {
                // ToDo 2: Create explicit intent to open a new activity
                context.startActivity(Intent(context, MainActivity::class.java))
            },
            enabled = switchChecked, // ToDo 6: Disable buttons when switch is off
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
            Spacer(modifier = Modifier.width(10.dp)) // ToDo 3: Change spacing between icon and text
            Text("Go To Activity 2")
        }

        HorizontalDivider(thickness = DividerDefaults.Thickness) // ToDo 4: Add horizontal divider

        // ToDo 5: Fix the switch
        Switch(
            checked = switchChecked,
            onCheckedChange = { switchChecked = it },
            modifier = Modifier.padding(10.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BasicOperationsPreview() {
    BCS371_W7_Demo_NavTheme {
        BasicOperations("Android")
    }
}
