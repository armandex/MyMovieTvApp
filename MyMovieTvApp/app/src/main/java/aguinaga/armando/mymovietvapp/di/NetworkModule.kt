package aguinaga.armando.mymovietvapp.di

import aguinaga.armando.mymovietvapp.data.api.MovieServiceApi
import aguinaga.armando.mymovietvapp.utils.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.lang.RuntimeException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE = "https://api.themoviedb.org/3/"
    const val BASE_IMAGES = "https://image.tmdb.org/t/p/w500/"
    private const val BASE_MOVIES = ""
    private const val SECONDS_TO_TIME_OUT: Long = 600

    @Singleton
    @Provides
    fun genericHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun genericSafeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(SECONDS_TO_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(SECONDS_TO_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(SECONDS_TO_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization","Bearer ${Constantes.myBearerToken}")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }
    /*@Singleton
    @Provides
    fun genericUnSafeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder{
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder.connectTimeout(SECONDS_TO_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(SECONDS_TO_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(SECONDS_TO_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }*/
    @Singleton
    @Provides
    fun getRetrofit( genericOkHttpClient: OkHttpClient): MovieServiceApi {
        return Retrofit.Builder()
            .baseUrl(BASE)
            .client(genericOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieServiceApi::class.java)
    }


}