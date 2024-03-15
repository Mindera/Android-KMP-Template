package com.mindera.kmpexample.launches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.mindera.kmpexample.composables.CurrencyExchangeScreen
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import com.mindera.precompose.navigation.BackHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    val viewModel : CurrencyExchangeViewModel by inject()
}

@Composable
fun CurrencyExchangeScene(onBack: (() -> Unit)) {
    BackHandler(onBack = onBack)

    val viewModel = remember { KoinHelper().viewModel }
    val state = viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Currency Exchange",
            fontSize = 40.sp,
        )
        CurrencyExchangeScreen(
            launches = state.value.launches
        )
    }
}
