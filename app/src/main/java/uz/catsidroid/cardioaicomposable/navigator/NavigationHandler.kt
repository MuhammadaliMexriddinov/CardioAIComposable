package uz.catsidroid.cardioaicomposable.navigator

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias NavigationArgs = Navigator.() -> Unit

interface NavigationHandler {
    val uiNavigator: SharedFlow<NavigationArgs>
}