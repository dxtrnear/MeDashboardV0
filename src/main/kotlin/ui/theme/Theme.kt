// File: src/main/kotlin/ui/theme/Theme.kt

package ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = HealthGreen,
    secondary = LightBlue,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun medashboardTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        content = content
    )
}