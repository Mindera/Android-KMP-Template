package com.mindera.kmpexample.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindera.compose.collections.ImmutableList
import com.mindera.kmpexample.launches.domain.model.CurrencyExchangeResponseItem
import com.mindera.kmpexample.launches.domain.model.RatesItem

@Composable
fun CurrencyExchangeScreen(
    modifier: Modifier = Modifier,
    currencies: ImmutableList<CurrencyExchangeResponseItem>,
) {
    val lazyListState = rememberLazyListState()

    val isExpandedMap = remember {
        List(currencies.size) { index: Int -> index to false }
            .toMutableStateMap()
    }

    if (currencies.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            state = lazyListState,
        ) {

            currencies.forEachIndexed { index, currencies ->
                item {
                    heading(currencies = currencies, isExpandedMap, index)
                }

                items(currencies.rates) {item ->
                    AnimatedVisibility(
                        visible = isExpandedMap[index] ?: false
                    ) {
                        expandableList(item)
                    }
                }
            }
        }
    }
}

@Composable
fun heading(
    currencies: CurrencyExchangeResponseItem,
    isExpandedMap: SnapshotStateMap<Int, Boolean>,
    index:Int
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier =  Modifier.fillMaxWidth().padding(15.dp).clickable {
                if (isExpandedMap[index] == null){
                    isExpandedMap[index] = true
                } else {
                    isExpandedMap[index] = !(isExpandedMap[index] ?: true)
                }
            },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = currencies.effectiveDate, color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp)
            Icon(
                imageVector = if (isExpandedMap[index] == null || !isExpandedMap[index]!!) {
                    Icons.Outlined.KeyboardArrowDown
                } else {
                    Icons.Outlined.KeyboardArrowUp
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun expandableList(item: RatesItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.code, color = Color(0xFF10D596), fontSize = 22.sp)
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 18.dp)
        ) {
            Text(text = item.currency.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }, color = Color.Gray, fontSize = 18.sp)
        }
        Text(item.currencyRate.toString(), color = Color(0xFF037DF0), fontSize = 20.sp)
    }
}
