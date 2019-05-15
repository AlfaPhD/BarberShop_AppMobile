package com.ope.mobile_ope.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ope.mobile_ope.Models.Agenda

@Database(entities = arrayOf(Agenda::class), version = 2)
abstract class AgendaDatabase : RoomDatabase(){
    abstract fun agendaDAO(): AgendaDAO
}