package com.ope.mobile_ope

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutoService {

    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getProduto(context: Context) : List<Produto> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apiproduto"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            return parserJson<List<Produto>>(json)
        } else {
            return ArrayList<Produto>()
        }
    }
    fun save(produto: Produto): Response {
        val json = HttpHelper.post("$host/apiproduto/", produto.toJson())
        return parserJson<Response>(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}