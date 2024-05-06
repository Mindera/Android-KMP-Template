package com.mindera.kmpexample.launches

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.mindera.kmpexample.features.launches.presentation.R


@Composable
fun HomeBottomBar(onNavBarClick: (Int) -> Unit) {

    var selectedItem by remember { mutableStateOf(0) }

    val bottomNavItems = listOf(
        BottomNavItem(
            name = stringResource(R.string.currency_exchange),
            route = stringResource(R.string.currency_exchange),
            icon = ImageVector.vectorResource(id = R.drawable.ic_currency_24),
        ),
        BottomNavItem(
            name = stringResource(R.string.settings),
            route = stringResource(R.string.settings),
            icon = ImageVector.vectorResource(id = R.drawable.ic_setting_24),
        ),
    )

    NavigationBar(
        containerColor =
        MaterialTheme.colorScheme.primaryContainer
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    disabledIconColor = Color.LightGray,
                    disabledTextColor = Color.LightGray
                ),
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onNavBarClick.invoke(selectedItem)
                },
                label = {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "${item.name} Icon",
                    )
                }
            )
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)
