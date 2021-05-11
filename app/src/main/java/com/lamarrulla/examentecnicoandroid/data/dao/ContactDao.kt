package com.lamarrulla.examentecnicoandroid.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lamarrulla.examentecnicoandroid.data.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    fun getAll(): List<Contact>

    @Query("SELECT * FROM contact WHERE name LIKE :first ")
    fun findByName(first: String): List<Contact>

    @Insert
    fun insert(vararg contact: Contact)

    @Insert
    fun insertAll(contacts: List<Contact>)

    @Delete
    fun delete(contact: Contact)
}
