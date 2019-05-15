package com.ope.mobile_ope.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ope.mobile_ope.Models.Cabeleleiro

@Database(entities = arrayOf(Cabeleleiro::class), version = 1)
abstract class CabeleleiroDatabase : RoomDatabase(){
    abstract fun cabeleleiroDAO():CabeleleiroDAO
}