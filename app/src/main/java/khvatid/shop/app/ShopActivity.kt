package khvatid.shop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import khvatid.shop.ui.theme.ShopTheme
import javax.inject.Inject

@AndroidEntryPoint
class ShopActivity @Inject constructor() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContent {
            ShopTheme {
                ShopApp()
            }
        }
    }
}