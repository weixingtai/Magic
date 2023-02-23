package com.suromo.magic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.suromo.magic.db.AppDatabase
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.ui.theme.MagicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lotteryDao = AppDatabase.getInstance(this).lotteryDao()
        val lotteries: List<Lottery> = lotteryDao.getLotteries()

        Log.d("wxt","shujuk")
        Log.d("wxt", "data:$lotteries")

        setContent {
            MagicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MagicTheme {
        Greeting("Android")
    }
}