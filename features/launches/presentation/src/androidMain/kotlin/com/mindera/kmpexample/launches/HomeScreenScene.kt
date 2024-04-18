package com.mindera.kmpexample.launches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mindera.kmpexample.composables.CurrencyExchangeScreen
import com.mindera.kmpexample.launches.currencyexchange.CurrencyExchangeHeaderScene
import com.mindera.kmpexample.launches.currencyexchange.CurrencyExchangeTabRowScene
import com.mindera.kmpexample.launches.setting.SettingsScene
import com.mindera.kmpexample.launches.viewmodel.CurrencyExchangeViewModel
import com.mindera.precompose.navigation.BackHandler
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.lineSeries
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    val viewModel: CurrencyExchangeViewModel by inject()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenScene(onBack: (() -> Unit)) {
    BackHandler(onBack = onBack)

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        2
    })
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

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    )
    {
        CurrencyExchangeHeaderScene()
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxSize().weight(1f)
        ) { page ->
            if (page == 0) {
                Column(modifier = Modifier.fillMaxSize()) {
                    CurrencyExchangeTabRowScene {
                        tabClick = it
                    }
                    if (tabClick == 0) {
                        CartesianChartHost(
                            modifier = Modifier.fillMaxHeight()
                                .background(MaterialTheme.colorScheme.onBackground),
                            chart = rememberCartesianChart(
                                rememberLineCartesianLayer(
                                    listOf(
                                        rememberLineSpec(
                                            shader = DynamicShaders.color(
                                                MaterialTheme.colorScheme.surface
                                            ),
                                        )
                                    )
                                ),
                                startAxis = rememberStartAxis(
                                    guideline = rememberAxisGuidelineComponent(color = Color.Gray),
                                    axis = rememberAxisGuidelineComponent(color = Color.Gray),
                                    label = rememberAxisLabelComponent(color = Color.Gray)
                                ),
                                bottomAxis = rememberBottomAxis(
                                    guideline = rememberAxisGuidelineComponent(color = Color.Gray),
                                    axis = rememberAxisGuidelineComponent(color = Color.Gray),
                                    label = rememberAxisLabelComponent(color = Color.Gray),
                                    valueFormatter = bottomAxisValueFormatter,
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
            } else {
                SettingsScene{
                    coroutineScope.launch {
                        tabClick = 0
                        pagerState.scrollToPage(0)
                    }
                }
            }
        }

        HomeBottomBar {
            coroutineScope.launch {
                pagerState.scrollToPage(it)
                tabClick = 0
            }
        }
    }
}
