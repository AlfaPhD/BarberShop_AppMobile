package com.ope.mobile_ope.Database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ope.mobile_ope.Models.Cliente

@Dao
interface ClienteDAO {
    @Query("SELECT * FROM cliente where id = :id")
    fun getById(id: Long) : Cliente?
    @Query("SELECT * FROM cliente")
    fun findAll(): List<Cliente>
    @Insert
    fun insert(cliente: Cliente)
    @Delete
    fun delete(cliente: Cliente)
}