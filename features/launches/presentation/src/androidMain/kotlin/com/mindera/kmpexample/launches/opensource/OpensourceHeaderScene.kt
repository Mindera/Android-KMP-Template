package com.mindera.kmpexample.launches.opensource

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindera.kmpexample.features.launches.presentation.R
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun OpensourceHeaderScene(navigator: Navigator) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.mindera),
            modifier = Modifier.height(50.dp).clickable {
                navigator.goBack()
            },
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Box(modifier = Modifier.fillMaxWidth(), Alignment.Center){
            Text(
                text = stringResource(R.string.open_source),
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.secondaryContainer).height(10.dp)
            .fillMaxWidth()
    )
}
