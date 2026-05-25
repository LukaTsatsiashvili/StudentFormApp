package com.example.studentformapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val viewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentFormApp(viewModel)
            }
        }
    }
}

@Composable
fun StudentFormApp(viewModel: FormViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Student Registration",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextPrimary
        )
        Text(
            text = "Provide your valid information to submit the system form.",
            fontSize = 14.sp,
            color = Color(0xFF64748B),
            modifier = Modifier.padding(bottom = 6.dp)
        )

        CustomInputField(
            value = viewModel.nameState,
            onValueChange = { viewModel.nameState = it },
            label = "Full Name",
            placeholder = "e.g., John Doe"
        )

        CustomInputField(
            value = viewModel.emailState,
            onValueChange = { viewModel.emailState = it },
            label = "Email Address",
            placeholder = "name@domain.com"
        )

        CustomDatePickerField(
            dateValue = viewModel.dateState,
            onDateSelected = { viewModel.dateState = it }
        )

        RadioOptionsGroup(
            options = listOf("Android", "iOS", "Web"),
            selectedOption = viewModel.selectedOption,
            onOptionSelected = { viewModel.selectedOption = it }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "I accept the processing of my form inputs",
                color = TextPrimary,
                fontSize = 14.sp
            )
            Switch(
                checked = viewModel.isAgreed,
                onCheckedChange = { viewModel.isAgreed = it },
                colors = SwitchDefaults.colors(checkedThumbColor = BrandPrimary)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (viewModel.isFormValid()) {
                    Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary)
        ) {
            Text(
                text = "Submit Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}