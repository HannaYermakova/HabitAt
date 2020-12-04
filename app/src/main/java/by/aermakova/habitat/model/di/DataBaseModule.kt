package by.aermakova.habitat.model.di

import android.content.Context
import androidx.room.Room
import by.aermakova.habitat.model.db.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    fun providesAppDataBase(context: Context?): AppDataBase {
        return Room.databaseBuilder(context!!, AppDataBase::class.java, "database").build()
    }

    @Module
    class ContextModule(private val context: Context) {
        @Provides
        @Singleton
        fun providesContext(): Context {
            return context
        }
    }
}