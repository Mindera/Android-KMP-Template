package com.mindera.kmpexample.launches.setting

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindera.darkMode
import com.mindera.getAppSize
import com.mindera.getAppVersion
import com.mindera.isDarkMode
import com.mindera.kmpexample.features.launches.presentation.R
import com.mindera.kmpexample.launches.language.LocaleHelper
import com.mindera.kmpexample.launches.language.LocaleHelper.setLocale
import moe.tlaster.precompose.navigation.Navigator

@Suppress("MagicNumber")
@Composable
fun SettingScene(navigator: Navigator, uiUpdate: () -> Unit) {
    val context = LocalContext.current
    var checked by remember { mutableStateOf(isDarkMode(context)) }
    var expanded by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    val language = if (LocaleHelper.getLanguageSharedPreference(context) == "en") {
        stringResource(R.string.english)
    } else {
        stringResource(R.string.portuguese)
    }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        card(height = 60.dp) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.dark_theme),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Switch(checked = checked,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.surface,
                        checkedBorderColor = MaterialTheme.colorScheme.surface
                    ),
                    onCheckedChange = {
                        checked = it
                        darkMode(context)
                        (context as? Activity)?.recreate()
                        uiUpdate()
                    })
            }
        }

        card(height = 60.dp) {
            Column(modifier = Modifier.fillMaxSize().clickable { expanded = !expanded }) {
                Row(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stringResource(R.string.language),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        language,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.english)) },
                        onClick = {
                            setLocale(context, "en")
                            (context as? Activity)?.recreate()
                            uiUpdate()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.portuguese)) },
                        onClick = {
                            setLocale(context, "pt")
                            (context as? Activity)?.recreate()
                            uiUpdate()
                        }
                    )
                }
            }
        }

        card(height = 60.dp) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.app_size),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    getAppSize(context),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }
        }

        card(height = 60.dp) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.app_version),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    getAppVersion(context),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }
        }

        card(height = 60.dp) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.repository),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    stringResource(R.string.github),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.clickable {
                        uriHandler.openUri("https://github.com/Mindera/Android-KMP-Template")
                    }
                )
            }
        }

        card(height = 60.dp) {
            Row(
                modifier = Modifier.fillMaxSize().clickable {
//                    screen.invoke(NavigationSelector.Opensource)
                    navigator.navigate("opensource")
                }.padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    stringResource(R.string.open_source),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Icon(
                    modifier = Modifier.rotate(270f),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down_24),
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun card(height: Dp = Dp.Unspecified, content: @Composable() () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth().height(height),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(2.dp),
        ) {
            content.invoke()
        }
    }
}
