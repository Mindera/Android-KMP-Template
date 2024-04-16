package com.mindera.kmpexample.launches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mindera.kmpexample.features.launches.presentation.R

@Composable
fun HomeTabRowScene(tabClick: (Int) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(R.string.graph_view), stringResource(R.string.list_view))
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
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                        color = Color.Black
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                        tabClick.invoke(tabIndex)
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray,
                )
            }
        }
    }
}
