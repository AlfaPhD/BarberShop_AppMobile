package com.ope.mobile_ope.Controladores

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.ope.mobile_ope.Models.Servico
import com.ope.mobile_ope.R
import com.ope.mobile_ope.Utils.DebugActivity

class ServicoActivity : DebugActivity() {

    var servico: Servico? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servico)

        // recuperar onjeto de Servico da Intent
        servico = intent.getSerializableExtra("servico") as Servico

        // configurar título com nome da Servico e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = servico?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeDisciplina)
        texto.text = servico?.nome
        var imagem = findViewById<ImageView>(R.id.imagemDisciplina)
/*        Picasso.with(this).load(servico?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
                */
    }
}
