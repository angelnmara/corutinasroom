package com.lamarrulla.examentecnicoandroid

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lamarrulla.examentecnicoandroid.data.dao.ContactDao
import com.lamarrulla.examentecnicoandroid.data.dao.UserDao
import com.lamarrulla.examentecnicoandroid.data.model.Contact
import com.lamarrulla.examentecnicoandroid.data.model.User

@Database(entities = arrayOf(User::class, Contact::class), version = 2)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
        abstract fun contactDao():ContactDao

        companion object{
            private var INSTANCE: AppDatabase? = null
            fun getDatabase(context: Context):AppDatabase{
                INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "dbExamenTecnico")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                return INSTANCE!!
            }
        }
    }




