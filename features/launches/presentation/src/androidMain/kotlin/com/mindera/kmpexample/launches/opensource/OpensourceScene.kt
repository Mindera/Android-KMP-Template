package com.mindera.kmpexample.launches.opensource

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.LibraryDefaults
import moe.tlaster.precompose.navigation.Navigator


@Composable
fun OpensourceScene (navigator: Navigator) {
    Column (modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
        OpensourceHeaderScene(navigator = navigator)
        LibrariesContainer(
            modifier = Modifier.fillMaxSize(),
            colors = LibraryDefaults.libraryColors(
                contentColor= MaterialTheme.colorScheme.onPrimary,
                badgeBackgroundColor= MaterialTheme.colorScheme.secondaryContainer,
                badgeContentColor = Color.Black,
                dialogConfirmButtonColor = MaterialTheme.colorScheme.surface
            ),
        )
    }

}
