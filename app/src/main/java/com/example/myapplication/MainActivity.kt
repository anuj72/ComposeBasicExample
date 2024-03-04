package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainVM = MainVm()
                    AppUi(mainVM)
                }
            }
        }
    }
}

@Composable
fun AppUi(mainVM: MainVm) {
    val dataCollected = mainVM.listFlow.collectAsState().value

    LazyRow {
        items(dataCollected.size ?: 0) { index ->
            val color = if (dataCollected.getOrNull(index)?.isCheck == true) {
                Color.Blue
            } else {
                Color.Gray

            }
            Button(
                modifier = Modifier.padding(horizontal = 6.dp),
                onClick = {
                     mainVM.doCheck(index)
                },
                content = {
                    Text(text = dataCollected.getOrNull(index)?.txt.toString(), fontSize = 11.sp)
                },
                colors = ButtonDefaults.buttonColors(containerColor = color),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            )
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
      //  AppUi()
    }
}