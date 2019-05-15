package com.ope.mobile_ope.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ope.mobile_ope.Models.Servico

@Database(entities = arrayOf(Servico::class), version = 5)
abstract class ServicoDatabase : RoomDatabase() {
    abstract fun servicoDAO(): ServicoDAO
}