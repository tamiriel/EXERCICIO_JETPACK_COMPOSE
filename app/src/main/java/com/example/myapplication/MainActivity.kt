package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraApp()
        }
    }
}

@Composable
fun CalculadoraApp() {
    var input by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val botoes = listOf(
        listOf("1", "2", "3", "+"),
        listOf("4", "5", "6", "-"),
        listOf("7", "8", "9", "*"),
        listOf("C", "0", "=", "/")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = if (resultado.isEmpty()) input else resultado,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.Black
        )

        botoes.forEach { linha ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                linha.forEach { simbolo ->
                    Button(
                        onClick = {
                            when (simbolo) {
                                "=" -> {
                                    resultado = calcularResultado(input)
                                    input = ""
                                }
                                "C" -> {
                                    input = ""
                                    resultado = ""
                                }
                                else -> input += simbolo
                            }
                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .size(80.dp)
                            .padding(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text(text = simbolo, fontSize = 24.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

fun calcularResultado(expressao: String): String {
    return try {
        val resultado = expressao.replace("x", "*").toDouble()
        resultado.toString()
    } catch (e: Exception) {
        "Erro"
    }
}
