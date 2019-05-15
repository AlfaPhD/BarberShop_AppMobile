package com.ope.mobile_ope.Controladores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ope.mobile_ope.Models.Servico
import com.ope.mobile_ope.R
import com.ope.mobile_ope.Service.ServicoService
import kotlinx.android.synthetic.main.activity_cadastro_servico.*


class ServicoCadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_servico)
        setTitle("Novo Servico")
        btnSalvarServico.setOnClickListener {
            val servico = Servico()
            servico.nome = edtNomeServico.text.toString()
            servico.valor = edtValorServico.text.toString()
            taskAtualizar(servico)
        }
    }
    private fun taskAtualizar(servico: Servico) {
// Thread para salvar a disciplina
        Thread {
            ServicoService
                    .save(servico)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}