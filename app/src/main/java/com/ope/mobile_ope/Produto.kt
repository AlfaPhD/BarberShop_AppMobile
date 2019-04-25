package com.ope.mobile_ope

import com.google.gson.GsonBuilder
import java.io.Serializable

class Produto : Serializable {
    var id:Long = 0
    var nome = ""
    var quantidade = ""
    var valor_unitario = ""
    var especificacao = ""
    var validade_produto = ""


    override fun toString(): String {
        return "Nome: $nome"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}