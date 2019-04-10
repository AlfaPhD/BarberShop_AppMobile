package com.ope.mobile_ope


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var produtos = listOf<Produto>()
    var recyclerProdutos: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args:Bundle? = intent.extras
        // recuperar o parâmetro do tipo String

        val nome = args?.getString("nome")

        // recuperar parâmetro simplificado
        val numero = intent.getIntExtra("nome",0)

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Produtos"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        // configurar cardview
        recyclerProdutos = findViewById<RecyclerView>(R.id.recyclerProdutos)
        recyclerProdutos?.layoutManager = LinearLayoutManager(context)
        recyclerProdutos?.itemAnimator = DefaultItemAnimator()
        recyclerProdutos?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        // task para recuperar as disciplinas
        taskProdutos()
    }

    fun taskProdutos() {
        this.produtos = ProdutoService.getProduto(context)
        // atualizar lista
        recyclerProdutos?.adapter = ProdutoAdapter(produtos) {onClickProdutos(it)}
    }

    // tratamento do evento de clicar em uma disciplina
    fun onClickProdutos(produto: Produto) {
        Toast.makeText(context, "Clicou disciplina ${produto.nomeProduto}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    // configuraçao do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layourMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_agendamentos -> {
                val intent = Intent(context, QuartaTelaActivity::class.java)
                val params = Bundle()
                params.putString("titulo", "Agendar")
                Toast.makeText(context, "Agendamentos", Toast.LENGTH_LONG).show()
                intent.putExtras(params)
                startActivity(intent)
                finish();
            }
            R.id.nav_servicos -> {
                val intent = Intent(context, QuartaTelaActivity::class.java)
                val params = Bundle()
                params.putString("titulo", "Serviços")
                Toast.makeText(context, "Serviços", Toast.LENGTH_LONG).show()
                intent.putExtras(params)
                startActivity(intent)
                finish();
            }
            R.id.nav_produtos -> {
                Toast.makeText(this, "Clicou Produtos", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_mensagens -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_localizacao -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_config -> {
                val intent = Intent(context, TelaConfiguracaoActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_sair -> {
                val returnIntent = Intent();
                returnIntent.putExtra("result","Saída do App");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                Toast.makeText(this, "Clicou em sair", Toast.LENGTH_SHORT).show()
            }
        }

        // fecha menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {

            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            val intent = Intent(context, TelaConfiguracaoActivity::class.java)
            startActivity(intent)
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }else if (id == R.id.action_adicionar) {
            val intent = Intent(context, SegundaTelaActivity::class.java)
            startActivity(intent)
            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}

//
//class TelaInicialActivity : DebugActivity() {
//
//
//    private val context: Context get() = this
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_tela_inicial)
//
//        // acessar parametros da intnet
//        // intent é um atributo herdado de Activity
//        val args = intent.extras
//        // recuperar o parâmetro do tipo String
//        /*val nome = args.getString("nome")
//
//        // recuperar parâmetro simplificado
//        val numero = intent.getIntExtra("nome",0)
//
//        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
//        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()*/
//
//        val mensagem = findViewById<TextView>(R.id.mensagemInicial)
//        mensagem.text = "Bem vindo"
//
//        val att_mensagem = findViewById<TextView>(R.id.attInicial)
//        att_mensagem.text = "Carregando"
//
//        val botaoSair = findViewById<Button>(R.id.botaoSair)
//        botaoSair.setOnClickListener {cliqueSair()}
//
//        val button_agendamento = findViewById<Button>(R.id.button_agendamento)
//        button_agendamento.setOnClickListener {cliqueAgendar()}
//
//        val button_servico = findViewById<Button>(R.id.button_servico)
//        button_servico.setOnClickListener {cliqueServico()}
//
//        val button_produto = findViewById<Button>(R.id.button_produto)
//        button_produto.setOnClickListener {cliqueProduto()}
//
//        // colocar toolbar
//        var toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//
//
//        // alterar título da ActionBar
//        supportActionBar?.title = "Tela Inicial"
//
//        // up navigation
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//
//    }
//
//    fun cliqueAgendar() {
//        val intent = Intent(context, QuartaTelaActivity::class.java)
//        val params = Bundle()
//        params.putString("titulo", "Agendar")
//        Toast.makeText(context, "Agendamentos", Toast.LENGTH_LONG).show()
//        intent.putExtras(params)
//        startActivity(intent)
//        finish();
//    }
//
//    fun cliqueServico() {
//        val intent = Intent(context, QuartaTelaActivity::class.java)
//        val params = Bundle()
//        params.putString("titulo", "Serviços")
//        Toast.makeText(context, "Serviços", Toast.LENGTH_LONG).show()
//        intent.putExtras(params)
//        startActivity(intent)
//        finish();
//    }
//
//    fun cliqueProduto() {
//        val intent = Intent(context, ProdutoActivity::class.java)
//        val params = Bundle()
//        params.putString("titulo", "Produtos")
//        Toast.makeText(context, "Produtos", Toast.LENGTH_LONG).show()
//        intent.putExtras(params)
//        startActivity(intent)
//        finish();
//    }
//
//    fun cliqueSair() {
//        val returnIntent = Intent();
//        returnIntent.putExtra("result","Saída do BarberShopApp");
//        setResult(Activity.RESULT_OK,returnIntent);
//        finish();
//    }
//
//    // método sobrescrito para inflar o menu na Actionbar
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        // infla o menu com os botões da ActionBar
//        menuInflater.inflate(R.menu.menu_main, menu)
//        // vincular evento de buscar
//        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                // ação enquanto está digitando
//                Toast.makeText(context, "Busca: $newText", Toast.LENGTH_LONG).show()
//                return false
//            }
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                // ação  quando terminou de buscar e enviou
//                return false
//            }
//
//        })
//        return true
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        // id do item clicado
//        val id = item?.itemId
//        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
//        // a comparação é feita com o recurso de id definido no xml
//        if  (id == R.id.action_buscar) {
//            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
//        } else if (id == R.id.action_atualizar) {
//            var progressBarStatus = 0
//            var dummy:Int = 0
//            Thread(Runnable {
//
//                this@TelaInicialActivity.runOnUiThread(java.lang.Runnable {
//                    progressBar1.visibility = VISIBLE
//
//                })
//                // dummy thread mimicking some operation whose progress can be tracked
//                while (progressBarStatus < 100) {
//                    // performing some dummy operation
//                    try {
//                        dummy = dummy+10
//                        Thread.sleep(1000)
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace()
//                    }
//                    // tracking progress
//                    progressBarStatus = dummy
//
//                    // Updating the progress bar
//                    progressBar1.progress = progressBarStatus
//                }
//
//                this@TelaInicialActivity.runOnUiThread(java.lang.Runnable {
//                    progressBar1.visibility = GONE
//                })
//
//            }).start()
//            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
//        } else if (id == R.id.action_config) {
//            val intent = Intent(context, TelaConfiguracaoActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
//        } else if (id == R.id.action_adicionar) {
//            val intent = Intent(context, SegundaTelaActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()
//        }
//        // botão up navigation
//        else if (id == android.R.id.home) {
//            finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//
//}