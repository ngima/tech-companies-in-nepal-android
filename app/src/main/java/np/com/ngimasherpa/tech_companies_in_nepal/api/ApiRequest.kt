package np.com.ngimasherpa.tech_companies_in_nepal.api

import android.content.Context
import android.util.Log

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.util.Calendar
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import np.com.ngimasherpa.tech_companies_in_nepal.exception.NetworkConnectionException
import np.com.ngimasherpa.tech_companies_in_nepal.model.Company
import np.com.ngimasherpa.tech_companies_in_nepal.utils.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequest {
    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null
    internal lateinit var context: Context

    val companies: Observable<List<Company>>
        get() = if (Utils.isThereInternetConnection(context))
            apiService!!.getCompanies()
        else
            Observable.error(NetworkConnectionException(ERROR_NO_INTERNET_CONNECTION))

    companion object {

        private val TAG = "ApiRequest"
        private val ERROR_NO_INTERNET_CONNECTION = "Sorry, No Internet Connection detected. Please reconnect and try again."

        fun getInstance(context: Context, @Url url: String): ApiRequest {
            var instance = ApiRequest()
            instance.retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                            .setLenient()
                            .create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient.Builder()
                            .addInterceptor(HeaderInterceptor())
                            .addInterceptor(HttpLoggingInterceptor())
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build())
                    .build()
            instance.apiService = instance!!.retrofit!!.create(ApiService::class.java)
            instance.context = context

            return instance
        }
    }
}