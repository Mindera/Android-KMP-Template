import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.mindera.kmpexample.presentation.App
import moe.tlaster.precompose.PreComposeWindow

fun main() = application {
    val windowState = rememberWindowState()

    PreComposeWindow(
        state = windowState,
        title = "CurrencyExchange",
        onCloseRequest = ::exitApplication
    ) {
        MaterialTheme {
            App()
        }
    }
}
