package br.com.marcos2silva.marvel.interceptor

import br.com.marcos2silva.marvel.extensions.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

private const val PRIVATE_KEY = "255cdf26953c469f57e4ce0e0d075426679288f2"
private const val API_KEY = "f4d6d03af5ee88009cf06406e6597ec9"

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", "$ts$PRIVATE_KEY$API_KEY".md5())
            .build()

        return chain.proceed(original.newBuilder().url(url).build())
    }
}