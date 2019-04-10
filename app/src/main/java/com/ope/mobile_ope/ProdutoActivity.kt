package com.ope.mobile_ope

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class ProdutoActivity : DebugActivity() {

    var produto: Produto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        // recuperar onjeto de Disciplina da Intent
        produto = intent.getSerializableExtra("produto") as Produto

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = produto?.nomeProduto

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeProduto)
        texto.text = produto?.nomeProduto
        var imagem = findViewById<ImageView>(R.id.imagemProduto)
        Picasso.with(this).load(produto?.foto).fit().into(imagem,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}

                override fun onError() { }
            })
    }
}