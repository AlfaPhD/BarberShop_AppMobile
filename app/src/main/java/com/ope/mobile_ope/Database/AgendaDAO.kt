package com.ope.mobile_ope.Database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ope.mobile_ope.Models.Agenda


@Dao
interface AgendaDAO {
    @Query("SELECT * FROM agenda where id = :id")
    fun getById(id: Long) : Agenda?
    @Query("SELECT * FROM agenda")
    fun findAll(): List<Agenda>
    @Insert
    fun insert(agenda: Agenda)
    @Delete
    fun delete(agenda: Agenda)
}