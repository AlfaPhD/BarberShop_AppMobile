package com.ope.mobile_ope.Controladores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ope.mobile_ope.Models.Cliente
import com.ope.mobile_ope.R
import com.ope.mobile_ope.Service.ClienteService
import kotlinx.android.synthetic.main.activity_cadastro_cliente.*

class ClienteCadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cliente)
        setTitle("Nova Cliente")
        btnSalvarCliente.setOnClickListener {
            val cliente = Cliente()
            cliente.nome = edtNomeCliente.text.toString()
            cliente.apelido = edtApelidoCliente.text.toString()
            cliente.senha = edtSenhaCliente.text.toString()
            cliente.email = edtEmailCliente.text.toString()
            cliente.celular = edtTelefoneCliente.text.toString()
            taskAtualizar(cliente)
        }
    }
    private fun taskAtualizar(cliente: Cliente) {
// Thread para salvar a disciplina
        Thread {
            ClienteService.save(cliente)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}