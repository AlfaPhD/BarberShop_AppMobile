package com.ope.mobile_ope.Controladores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ope.mobile_ope.Models.Cabeleleiro
import com.ope.mobile_ope.Models.Cliente
import com.ope.mobile_ope.R
import com.ope.mobile_ope.Service.CabeleleiroService
import com.ope.mobile_ope.Service.ClienteService
import kotlinx.android.synthetic.main.activity_cadastro_cabeleleiro.*
import kotlinx.android.synthetic.main.activity_cadastro_cliente.*

class CabeleleiroCadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cabeleleiro)
        setTitle("Novo Cabeleleiro")
        btnSalvarCabeleleiro.setOnClickListener {
            val cabeleleiro = Cabeleleiro()
            cabeleleiro.nome = edtNomeCabeleleiro.text.toString()
            cabeleleiro.senha = edtSenhaCabeleleiro.text.toString()
            cabeleleiro.email = edtEmailCabeleleliro.text.toString()
            cabeleleiro.celular = edtTelefoneCabeleleiro.text.toString()
            taskAtualizar(cabeleleiro)
        }
    }
    private fun taskAtualizar(cabeleleiro: Cabeleleiro) {
// Thread para salvar a disciplina
        Thread {
            CabeleleiroService.save(cabeleleiro)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}