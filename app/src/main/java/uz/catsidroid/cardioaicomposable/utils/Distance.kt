package uz.catsidroid.cardioaicomposable.utils

import kotlin.math.*

fun myDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val R = 6371.0 // Yer radiusi (km)

    val phi1 = Math.toRadians(lat1)
    val phi2 = Math.toRadians(lat2)

    val deltaPhi = Math.toRadians(lat2 - lat1)
    val deltaLambda = Math.toRadians(lon2 - lon1)

    val a = sin(deltaPhi / 2.0).pow(2) + cos(phi1) * cos(phi2) * sin(deltaLambda / 2.0).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    val distance = R * c
    return distance
}
//myDistance