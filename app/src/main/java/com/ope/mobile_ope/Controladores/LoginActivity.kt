package com.ope.mobile_ope.Controladores

import com.ope.mobile_ope.R
import com.ope.mobile_ope.Utils.DebugActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_login.*
import br.com.fernandosousa.lmsapp.Prefs

class LoginActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.imagem_login)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)


        val botaoLogin = findViewById<Button>(R.id.botao_login)

        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            checkLembrar.isChecked = lembrar
        }

        botaoLogin.setOnClickListener {onClickLogin() }

    }

    fun onClickLogin(){

        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        if(valorUsuario == "aluno" && valorSenha == "impacta") {
            // armazenar valor do checkbox
            Prefs.setBoolean("lembrar", checkLembrar.isChecked)
// verificar se é para lembrar nome e senha
            if (checkLembrar.isChecked) {
                Prefs.setString("lembrarNome", valorUsuario)
                Prefs.setString("lembrarSenha", valorSenha)
            } else {
                Prefs.setString("lembrarNome", "")
                Prefs.setString("lembrarSenha", "")
            }
            // criar intent
            val intent = Intent(context, TelaInicialActivity::class.java)
            // colocar parâmetros (opcional)
            val params = Bundle()
            params.putString("nome", valorUsuario)

            intent.putExtras(params)

            // fazer a chamada esperando resultado
            startActivityForResult(intent, 1)
        }else{
            Toast.makeText(context, "Usúario ou senha invalidos!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}