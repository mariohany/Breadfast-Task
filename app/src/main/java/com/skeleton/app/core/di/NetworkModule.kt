package  com.skeleton.app.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.skeleton.app.BuildConfig
import com.skeleton.app.core.data.remote.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(ChuckerInterceptor.Builder(context).build())
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
        resultCallAdapterFactory:ResultCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(resultCallAdapterFactory)
            .client(okHttpClient)
            .build()
    }
}