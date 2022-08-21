package com.esvisoftech.weatherapp.utils

import java.text.SimpleDateFormat
import kotlin.math.roundToInt

fun formatDate(timeStamp:Int):String{
    val sdf=SimpleDateFormat("EEE, MMM d")
    val date=java.util.Date(timeStamp.toLong()*1000)
    return sdf.format(date)
}

fun formatDateTime(timeStamp: Int):String{
    val sdf=SimpleDateFormat("hh:mm:aa")
    val date=java.util.Date(timeStamp.toLong()*1000)
    return sdf.format(date)
}

//fun formatDecimals(item:Double):String{
//    val fa=item.roundToInt()
//    val cel=(fa - 32.0) * 5.0 / 9.0
//    return "%.0f".format(cel)
////    return "%.0f".format(fa)
//}


fun formatDecimals(item:Double):String{

    return "%.0f".format(item)
}


