package com.ope.mobile_ope

import java.io.Serializable

class Produto : Serializable {
    var id:Long = 0
    var nomeProduto = ""
    var foto = ""
    var descricao = ""
    var preco = ""

    override fun toString(): String {
        return "Serviço: $nomeProduto"
    }
}