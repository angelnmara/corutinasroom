package com.lamarrulla.examentecnicoandroid.api

import com.lamarrulla.examentecnicoandroid.data.Result
import com.lamarrulla.examentecnicoandroid.data.model.Contact

class RepoImpl(private val dataSource: DataSource):Repo {
    override suspend fun getContacts(): Result<List<Contact>> {
        return dataSource.getContacts()
    }

    override suspend fun getContaactsRoom(): Result<List<Contact>> {
        return dataSource.getContactsRoom()
    }

    override suspend fun insertContactsRoom(listaContactos: List<Contact>) {
        dataSource.insertContantsIntoRoom(listaContactos)
    }

    override suspend fun getContactsByNameRoom(name:String): Result<List<Contact>> {
        return dataSource.getConstactsByNameRoom(name)
    }
}