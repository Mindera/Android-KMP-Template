package com.mindera.kmpexample.launches

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindera.kmpexample.features.launches.presentation.R

@Composable
fun HomeHeaderScene() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.mindera),
            contentDescription = stringResource(R.string.mindera),
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
}
