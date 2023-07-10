package com.example.vmoneyapp.di

import com.example.vmoneyapp.data.remote.PeopleCall
import com.example.vmoneyapp.data.remote.ApiDetails
import com.example.vmoneyapp.data.remote.RoomsCall
import com.example.vmoneyapp.data.repository.Repository
import com.example.vmoneyapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class) // Infore class Scope
class AppModule {

    @Provides
    fun provideOkHttpInstance() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideRetrofitInstance(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providePeopleAPI(
        retrofit: Retrofit
    ) : PeopleCall {
        return retrofit.create(PeopleCall::class.java)
    }

    @Provides
    fun provideRoomsAPI(
        retrofit: Retrofit
    ) : RoomsCall {
        return retrofit.create(RoomsCall::class.java)
    }

    @Provides
    fun provideRepository(
        peopleCall: PeopleCall,
        roomsCall: RoomsCall
    ) : Repository {
        return RepositoryImpl(peopleCall, roomsCall)
    }


}