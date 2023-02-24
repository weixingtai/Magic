package com.suromo.magic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.suromo.magic.log.MLog
import com.suromo.magic.ui.theme.MagicTheme
import com.suromo.magic.vm.LotteryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MagicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    viewModel: LotteryViewModel = hiltViewModel(),
) {
//    Log.d("wxt","进入主界面")
    MLog.d("进入主界面")
    val lotteries = viewModel.lotteries.observeAsState().value

//    val lotteries: LazyPagingItems<Lottery> = viewModel.lotteries.collectAsLazyPagingItems()
    Text(text = "Hello ${lotteries?.size}!")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MagicTheme {
        Greeting()
    }
}