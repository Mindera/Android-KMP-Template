package com.mindera.kmpexample.launches

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindera.kmpexample.composables.CurrencyExchangeScreen
import com.mindera.kmpexample.features.launches.presentation.R
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import com.mindera.precompose.navigation.BackHandler
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.lineSeries
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    val viewModel: CurrencyExchangeViewModel by inject()
}

@Composable
fun CurrencyExchangeScene(onBack: (() -> Unit)) {
    BackHandler(onBack = onBack)

    var checked by remember { mutableStateOf(true) }
    val viewModel = remember { KoinHelper().viewModel }
    val modelProducer = remember { CartesianChartModelProducer.build() }
    val lastTenState = viewModel.lastTenCurrencyStateFlow.collectAsState()
    val currentState = viewModel.currentCurrencyStateFlow.collectAsState()
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("10 days exchange", "Daily Chart")

    val list = ArrayList<Double>()
    currentState.value.launches.forEach {
        if (it.rates.isNotEmpty()) {
            it.rates.forEachIndexed { index, ratesItem ->
                list.add(ratesItem.currencyRate)
                if (index == it.rates.lastIndex) {
                    LaunchedEffect(Unit) {
                        modelProducer.tryRunTransaction {
                            lineSeries {
                                series(list)
                            }
                        }
                    }
                }
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.mindera),
                contentDescription = "heading",
                modifier = Modifier.height(50.dp)
            )
            Text(
                text = stringResource(R.string.mindera),
                fontSize = 25.sp,
            )
        }
        Box(
            modifier = Modifier.background(color = colorResource(R.color.yellow)).height(10.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = colorResource(R.color.lightYellow),
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    if (tabIndex < tabPositions.size) {
                        SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                            color = Color.Black
                        )
                    }
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray,
                    )
                }
            }
            when (tabIndex) {
                0 -> checked = true
                1 -> checked = false
            }
        }
        Box(modifier = Modifier.weight(weight = 1f).fillMaxWidth()) {
            if (checked) {
                CurrencyExchangeScreen(
                    currencies = lastTenState.value.launches
                )
            } else {
                CartesianChartHost(
                    chart = rememberCartesianChart(
                        rememberLineCartesianLayer(),
                        startAxis = rememberStartAxis(),
                        bottomAxis = rememberBottomAxis(),
                    ),
                    modelProducer = modelProducer,
                    zoomState = rememberVicoZoomState(zoomEnabled = false),
                )
            }
        }
        CurrencyExchangeBottomBar()
    }
}
