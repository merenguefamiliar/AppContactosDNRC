package com.example.proyecto.di

import android.app.Application
import androidx.room.Room
import com.example.proyecto.data.local.ContactDao
import com.example.proyecto.data.local.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //Modulo de Hilt
@InstallIn(SingletonComponent::class)// Contenedor para installar las dependencias único en toda la aplicación
object AppModule {
    //Módulo que construye la instancia de la BBDD
    @Provides
    @Singleton
    fun provideContactDatabase(context: Application): ContactDatabase {
        return Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contactos_database" //Nombre que almacenará la BBDD
        ).build()
    }

    //Módulo que provee el DAO
    @Provides
    @Singleton
    fun provideContactDao(db: ContactDatabase) : ContactDao {
        return db.contactDao()
    }
}