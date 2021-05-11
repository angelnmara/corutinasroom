package com.lamarrulla.examentecnicoandroid.api

import com.lamarrulla.examentecnicoandroid.AppDatabase
import com.lamarrulla.examentecnicoandroid.data.Result
import com.lamarrulla.examentecnicoandroid.data.model.Contact
import com.lamarrulla.examentecnicoandroid.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {
    suspend fun getContacts():Result<List<Contact>>{
        return Result.Success(RetrofitClient.webservice.getContact().toList())
    }

    suspend fun getContactsRoom():Result<List<Contact>>{
        return Result.Success(appDatabase.contactDao().getAll())
    }

    suspend fun insertContantsIntoRoom(contactos:List<Contact>){
        appDatabase.contactDao().insertAll(contactos)
    }

    suspend fun getConstactsByNameRoom(name:String):Result<List<Contact>>{
        return Result.Success(appDatabase.contactDao().findByName("%" + name + "%"))
    }

}