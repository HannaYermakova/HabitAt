package by.aermakova.habitat.model.di

import android.content.Context
import androidx.room.Room
import by.aermakova.habitat.model.db.AppDataBase
import by.aermakova.habitat.model.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides


@Module
class DataBaseModule {

    @ApplicationScope
    @Provides
    fun providesAppDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database").build()
    }
}