package br.com.justworks.volan2.main.presentation.bottombar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.justworks.volan2.R
import br.com.raqfc.movieapp.ui.presentation.bottombar.home.HomeMainNavView

sealed class MainNavigationBarItems(val iconVector: ImageVector, @StringRes val labelRes: Int, var selected: Boolean = false, val view: (@Composable () -> Unit) = {}) {
    object Home: MainNavigationBarItems(Icons.Filled.Home, R.string.main_nav_item_home, true, view = { HomeMainNavView() })
    object Quality: MainNavigationBarItems(Icons.Filled.CheckCircle, R.string.main_nav_item_quality, view = { QualityMainNavView() })
    object Billing: MainNavigationBarItems(Icons.Filled.Money, R.string.main_nav_item_billing, view = { BillingMainNavView() })
    object Schedule: MainNavigationBarItems(Icons.Filled.CalendarViewDay, R.string.main_nav_item_schedule, view = { ScheduleMainNavView() })
    object Config: MainNavigationBarItems(Icons.Filled.Settings, R.string.main_nav_item_config, view = { ConfigMainNavView() })


    companion object {
        fun getNavItems(): List<MainNavigationBarItems> {
            return listOf(
                Home,
                Quality,
                Billing,
                Schedule,
                Config
            )
        }

        fun List<MainNavigationBarItems>.unSelectAll(): List<MainNavigationBarItems> {
            for(item in this) {
                item.selected = false
            }
            return this
        }
    }
}