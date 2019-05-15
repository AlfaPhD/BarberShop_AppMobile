package com.ope.mobile_ope.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ope.mobile_ope.Models.Cliente

@Database(entities = arrayOf(Cliente::class), version = 3)
abstract class ClienteDatabase : RoomDatabase() {
    abstract fun clienteDAO(): ClienteDAO
}