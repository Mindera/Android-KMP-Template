package com.mindera.kmpexample.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mindera.compose.collections.ImmutableList
import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem

@Composable
fun CurrencyExchangeScreen(
    modifier: Modifier = Modifier,
    currencies: ImmutableList<CurrencyExchangeResponseItem>,
) {
    val lazyListState = rememberLazyListState()

    if (currencies.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            state = lazyListState,
        ) {
            currencies.forEach {
                itemsIndexed(items = it.rates, key = { index, _ -> index }) { index, launch ->
                    if (index != 0) {
                        Divider(thickness = 2.dp)
                    }
                    Text(it.no)
                    Text(launch.id)
                    Text(launch.currency.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    })
                    Text(launch.code, color = Color.Blue)
                    Text(launch.currencyRate.toString(), color = Color.Gray)
                }
            }
        }

    }
}
