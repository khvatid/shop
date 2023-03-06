package khvatid.shop.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import khvatid.shop.R


private val montserrat = FontFamily(
    Font(
        R.font.montserrat_black,
        weight = FontWeight.Black,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_extra_bold,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_extra_light,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ), Font(
        R.font.montserrat_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_semi_bold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    )
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.3).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.3).sp,
    ),
    displaySmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.3).sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.3).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.3).sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleSmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.3).sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp,
    ),
    bodySmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.3).sp,
    ),
    labelLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp,
    ),
    labelMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.3).sp,
    ),
    labelSmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.3).sp,
    ),
)
//(-0.3)
@Composable
@Preview(showSystemUi = true)
fun Preview() {
    ShopTheme() {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "- KINOVED DL",
                style = MaterialTheme.typography.displayLarge
            )

            Text(
                text = "- Sign up DM",
                style = MaterialTheme.typography.displayMedium
            )

            Text(
                text = "- Sign up DS",
                style = MaterialTheme.typography.displaySmall
            )

            Text(
                text = "- Sign up HL",
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "- KINOVED HM",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "- KINOVED HS",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "- KINOVED TL",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "- KINOVED TM",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "- KINOVED TS",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "- KINOVED BL",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "- KINOVED BM",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "- KINOVED BS",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "- KINOVED LL",
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = "- KINOVED LM",
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = "- KINOVED LS",
                style = MaterialTheme.typography.labelSmall
            )

        }
    }
}