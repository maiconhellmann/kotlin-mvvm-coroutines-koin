package com.maiconhellmann.architecture.injection.module

import com.google.gson.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.maiconhellmann.architecture.BuildConfig
import com.maiconhellmann.architecture.data.remote.endpoint.MovieWebService
import com.maiconhellmann.architecture.misc.RequestInterceptor
import com.maiconhellmann.architecture.misc.UnsafeOkHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val remoteDatasourceModule = applicationContext {

    //RequestInterceptor
    bean { provideRequestInterceptor() }

    //LoggingInterceptop
    bean { provideLoggingInterceptor() }

    // provided web components
    bean { provideOkHttpClient(get(), get()) }

    bean { provideGson() }

    bean { provideRemoteDataSource(get(), get()) }
}

/**
 * Prove o parser de Json para a aplicação
 */
fun provideGson(): Gson {
    val builder = GsonBuilder()

    builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
        json?.asJsonPrimitive?.asLong?.let {
            return@JsonDeserializer Date(it)
        }
    })

    builder.registerTypeAdapter(Date::class.java, JsonSerializer<Date> { date, _, _ ->
        JsonPrimitive(date.time)
    })

    return builder.create()
}


/**
 * Prove o interceptor das requisições. Utilizado para adicionar header de token, por exemplo.
 */
fun provideRequestInterceptor(): RequestInterceptor {
    return RequestInterceptor()
}

/**
 * Provê o interceptor de logging das requisições
 */
fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    //Adiciona log às requisições
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return logInterceptor
}

/**
 * Provê o httpClient padrão para o App
 */
fun provideOkHttpClient(requestInterceptor: RequestInterceptor,
                        logInterceptor: HttpLoggingInterceptor): OkHttpClient {

    val builder = UnsafeOkHttpClient.getUnsafeOkHttpClient()

    //Adiciona os interceptors
    builder.addInterceptor(logInterceptor)
    builder.addInterceptor(requestInterceptor)

    builder.connectTimeout(2, TimeUnit.MINUTES)
    builder.readTimeout(1, TimeUnit.MINUTES)
    builder.readTimeout(1, TimeUnit.MINUTES)

    return builder.build()
}

/**
 * Provê o endpoint service para a aplicação
 */
fun provideRemoteDataSource(okHttpClient: OkHttpClient, gson: Gson): MovieWebService {
    return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MovieWebService::class.java)
}
