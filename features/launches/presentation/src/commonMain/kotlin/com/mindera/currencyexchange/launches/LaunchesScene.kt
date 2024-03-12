package com.mindera.currencyexchange.launches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.mindera.client.configureHttpClient
import com.mindera.currencyexchange.composables.LaunchesScreen
import com.mindera.currencyexchange.launches.usecase.GetLaunchesUseCaseV1
import com.mindera.currencyexchange.launches.viewmodel.LaunchesViewModel
import com.mindera.datasource.remote.KtorLaunchesRemoteSource
import com.mindera.precompose.navigation.BackHandler
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel

// FIXME: Implement proper dependency injection (using kotlin-inject or Koin 🤷‍️)
val viewModel = LaunchesViewModel(
    getLaunches = GetLaunchesUseCaseV1(
        remote = KtorLaunchesRemoteSource(
            baseUrl = "https://api.nbp.pl/api/exchangerates/tables",
            client = configureHttpClient(OkHttp, 10_000L, LogLevel.ALL)
        )
    )
)

@Composable
fun LaunchesScene(onBack: (() -> Unit)) {
    BackHandler(onBack = onBack)

    val state = viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Currency Exchange",
            fontSize = 40.sp,
        )
        LaunchesScreen(
            launches = state.value.launches
        )
    }
}
