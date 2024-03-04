package com.mindera.spacex.composables

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
import com.mindera.spacex.launches.domain.model.Launch

@Composable
fun LaunchesScreen(
    modifier: Modifier = Modifier,
    launches: ImmutableList<Launch>,
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = lazyListState,
    ) {
        itemsIndexed(items = launches, key = { index, _ -> index }) { index, launch ->
            if (index != 0) {
                Divider(thickness = 2.dp)
            }
            Text(launch.id)
            Text(launch.name)
            Text(
                text = if (launch.success) "Successful" else "Unsuccessful",
                color = if (launch.success) Color.Green else Color.Red
            )
        }
    }
}
