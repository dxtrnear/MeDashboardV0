// File: src/main/kotlin/ui/theme/Theme.kt

package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.LocalContentColor

private val Colors = lightColors(
    primary = MedicalGreen,
    primaryVariant = DarkGreen,
    secondary = MedicalBlue,
    secondaryVariant = DarkBlue,
    background = Background,
    surface = Surface,
    error = Error,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onError = Color.White
)

@Composable
fun medashboardTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = Colors,
        content = content
    )
}