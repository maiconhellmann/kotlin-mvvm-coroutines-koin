package com.maiconhellmann.architecture.misc

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
        val newRequest = request?.newBuilder()

        //get the token
        try {
            newRequest?.addHeader("Accept", "application/json")

//            val token = dataBase.getToken().toBlocking().first()
//            val token = AutoVistoriaApplication.token

            //if token is not null try to add to header
//            token?.let {
//                newRequest?.addHeader(RemoteConstants.AUTHORIZATION, it.token)
//            }
        } catch (ex: Throwable) {
            Timber.w(ex, "Erro ao consultar token para o interceptop")
        }

        return chain?.proceed(newRequest!!.build())!!
    }
}