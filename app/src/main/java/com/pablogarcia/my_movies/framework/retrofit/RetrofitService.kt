package com.pablogarcia.my_movies.framework.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    /**
     * Create service adding url end point and header params
     *
     * @param serviceClass
     */
    fun <S> createService(serviceClass: Class<S>): S {
        val httpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        httpClient.addInterceptor { chain -> return@addInterceptor addHeaderParams(chain) }
        httpClient.connectTimeout(HTTP_TIMEOUT_SECS.toLong(), TimeUnit.SECONDS)
        httpClient.readTimeout(HTTP_TIMEOUT_SECS.toLong(), TimeUnit.SECONDS)
        val retrofit: Retrofit = builder
            .baseUrl(URL_BACKEND)
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    /**
     * Add header params to url
     *
     * @param chain
     * @return response
     */
    private fun addHeaderParams(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder().apply {
            addQueryParameter(HEADER_CLIENT, HEADER_CLIENT_VALUE)
            addQueryParameter(HEADER_STATUSES, HEADER_STATUSES_VALUE)
            addQueryParameter(HEADER_DEFINITIONS, HEADER_DEFINITIONS_VALUE)
            addQueryParameter(HEADER_EXTERNAL_CATEGORY_ID, HEADER_EXTERNAL_CATEGORY_ID_VALUE)
            addQueryParameter(HEADER_FILTER_EMPTY_CATEGORIES, HEADER_FILTER_EMPTY_CATEGORIES_VALUE)
        }.build()
        request.url(newUrl)

        return chain.proceed(request.build())
    }

    private const val HTTP_TIMEOUT_SECS = 30

    private const val URL_BACKEND = "https://smarttv.orangetv.orange.es/stv/api/rtv/v1/"

    private const val HEADER_CLIENT = "client"
    private const val HEADER_STATUSES = "statuses"
    private const val HEADER_DEFINITIONS = "definitions"
    private const val HEADER_EXTERNAL_CATEGORY_ID = "external_category_id"
    private const val HEADER_FILTER_EMPTY_CATEGORIES = "filter_empty_categories"

    private const val HEADER_CLIENT_VALUE = "json"
    private const val HEADER_STATUSES_VALUE = "published"
    private const val HEADER_DEFINITIONS_VALUE = "SD;HD;4K"
    private const val HEADER_EXTERNAL_CATEGORY_ID_VALUE = "SED_3880"
    private const val HEADER_FILTER_EMPTY_CATEGORIES_VALUE = "true"
}
