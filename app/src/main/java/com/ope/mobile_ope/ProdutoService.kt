package com.ope.mobile_ope

import android.content.Context

object ProdutoService {

    fun getProduto(context: Context) : List<Produto> {
        val produtos = mutableListOf<Produto>()
        for (i in 1..10){
            val d = Produto()
            d.nomeProduto = "Gel $i"
            d.descricao = "Descrição $i"
            d.foto = "https://d1nhio0ox7pgb.cloudfront.net/_img/o_collection_png/green_dark_grey/256x256/plain/shower_gel.png"
            d.preco = "Preço R$ $i"
            produtos.add(d)
        }
        return produtos
    }
}