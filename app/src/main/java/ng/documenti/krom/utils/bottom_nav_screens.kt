package ng.documenti.krom.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ng.documenti.krom.R

sealed class BottomNavScreens(val route: String, val title: String,@DrawableRes val icon : Int) {
    object Home : BottomNavScreens("home", "Home",R.drawable.anime)
    object Uploads : BottomNavScreens("uploads", "Uploads",R.drawable.upload)
    object Profile : BottomNavScreens("profile", "Profile",R.drawable.profile)
}