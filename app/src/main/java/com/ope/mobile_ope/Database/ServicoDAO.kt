package com.ope.mobile_ope.Database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ope.mobile_ope.Models.Servico

@Dao
interface ServicoDAO {
    @Query("SELECT * FROM servico where id = :id")
    fun getById(id: Long) : Servico?
    @Query("SELECT * FROM servico")
    fun findAll(): List<Servico>
    @Insert
    fun insert(servico: Servico)
    @Delete
    fun delete(servico: Servico)
}