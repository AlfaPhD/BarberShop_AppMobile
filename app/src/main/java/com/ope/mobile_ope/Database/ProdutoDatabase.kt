package com.ope.mobile_ope.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ope.mobile_ope.Models.Produto


@Database(entities = arrayOf(Produto::class), version = 4)
abstract class ProdutoDatabase : RoomDatabase() {
    abstract fun produtoDAO(): ProdutoDAO
}