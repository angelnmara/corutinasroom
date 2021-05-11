package com.lamarrulla.examentecnicoandroid.api

import com.lamarrulla.examentecnicoandroid.data.model.Contact
import com.lamarrulla.examentecnicoandroid.data.Result

interface Repo {
    suspend fun getContacts():Result<List<Contact>>
    suspend fun getContaactsRoom(): Result<List<Contact>>
    suspend fun insertContactsRoom(listaContactos:List<Contact>)
    suspend fun getContactsByNameRoom(name:String): Result<List<Contact>>
}