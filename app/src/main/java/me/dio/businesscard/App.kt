package me.dio.businesscard

import android.app.Application
import me.dio.businesscard.data.AppDataBase
import me.dio.businesscard.data.BusinessCardRepository

class App : Application() {
    private val dataBase by lazy { AppDataBase.getDataBase(this) }
    val repository by lazy { BusinessCardRepository(dataBase.businessDao()) }
}