package com.mindera.kmpexample.launches

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mindera.kmpexample.composables.CurrencyExchangeScreen
import com.mindera.kmpexample.launches.currencyexchange.CurrencyExchangeHeaderScene
import com.mindera.kmpexample.launches.currencyexchange.CurrencyExchangeTabRowScene
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import com.mindera.precompose.navigation.BackHandler
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.lineSeries
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    val viewModel: CurrencyExchangeViewModel by inject()
}

@Composable
fun HomeScreenScene(onBack: (() -> Unit)) {
    BackHandler(onBack = onBack)

    var tabClick by remember { mutableStateOf(0) }
    val viewModel = remember { KoinHelper().viewModel }
    val modelProducer = remember { CartesianChartModelProducer.build() }

    val lastTenState = viewModel.lastTenCurrencyStateFlow.collectAsState()
    val currentState = viewModel.currentCurrencyStateFlow.collectAsState()

    val list = ArrayList<Double>()
    val listCurrency = ArrayList<String>()
    val emptyList = emptyList<String>()
    var bottomAxisValueFormatter: AxisValueFormatter<AxisPosition.Horizontal.Bottom> =
        AxisValueFormatter { x, _, _ ->
            emptyList[x.toInt() % 0]
        }


    currentState.value.launches.forEach {
        if (it.rates.isNotEmpty()) {
            it.rates.forEachIndexed { index, ratesItem ->
                list.add(ratesItem.currencyRate)
                listCurrency.add(ratesItem.code)
                if (index == it.rates.lastIndex) {
                    LaunchedEffect(Unit) {
                        modelProducer.tryRunTransaction {
                            lineSeries {
                                series(list)
                            }
                        }
                    }

                    bottomAxisValueFormatter = AxisValueFormatter { x, _, _ ->
                        listCurrency[x.toInt() % listCurrency.size]
                    }
                }
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        CurrencyExchangeHeaderScene()
        CurrencyExchangeTabRowScene {
            tabClick = it
        }
        Box(modifier = Modifier.weight(weight = 1f).fillMaxWidth()) {
            if (tabClick == 0) {
                CartesianChartHost(
                    modifier = Modifier.fillMaxHeight().padding(bottom = 20.dp),
                    chart = rememberCartesianChart(
                        rememberLineCartesianLayer(),
                        startAxis = rememberStartAxis(),
                        bottomAxis = rememberBottomAxis(
                            valueFormatter = bottomAxisValueFormatter
                        ),
                    ),
                    modelProducer = modelProducer,
                    zoomState = rememberVicoZoomState(zoomEnabled = false),
                )
            } else {
                CurrencyExchangeScreen(
                    currencies = lastTenState.value.launches
                )
            }
        }
        HomeBottomBar()
    }
}
