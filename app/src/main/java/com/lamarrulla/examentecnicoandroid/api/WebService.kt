package com.lamarrulla.examentecnicoandroid.api

import com.lamarrulla.examentecnicoandroid.data.model.Contact
import retrofit2.http.GET

interface WebService {
    @GET("contacts.php")
    suspend fun getContact() : List<Contact>
}