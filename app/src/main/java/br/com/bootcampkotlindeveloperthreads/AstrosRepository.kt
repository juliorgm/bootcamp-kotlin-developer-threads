package br.com.bootcampkotlindeveloperthreads

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.tls.OkHostnameVerifier


class AstrosRepository {
    fun loadData(): List<AstroPeople> {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://api.open-notify.org/astros.json")
            .build()
        val response = client.newCall(request).execute()
        val result = parseJsonToResult(response.body?.string())
        return result.people
    }

    fun parseJsonToResult(json: String?)= Gson().fromJson(json, AstrosResult::class.java)
}