package com.lamarrulla.examentecnicoandroid.ui.contact

import android.util.Log
import androidx.lifecycle.*
import com.lamarrulla.examentecnicoandroid.api.Repo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import com.lamarrulla.examentecnicoandroid.data.Result
import com.lamarrulla.examentecnicoandroid.data.model.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val repo: Repo): ViewModel() {

    private val nameMutable = MutableLiveData<String>()

    init {
        getName("")
    }

    fun getName(name:String){
        nameMutable.value = name
    }

    val fetchContactList = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try{
            emit(repo.getContacts())
        }catch (e: Exception){
            Log.d("", e.toString())
            //emit(Resource.Failure(e))
        }
    }

    fun guardarContactList(contactList: List<Contact>){
        viewModelScope.launch {
            repo.insertContactsRoom(contactList)
        }
    }

    val fetchContactListRoom = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try{
            emit(repo.getContaactsRoom())
        }catch (e: Exception){
            Log.d("", e.toString())
            //emit(Resource.Failure(e))
        }
    }

    val fetchContactListByNameRoom = nameMutable.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Result.Loading())
            try{
                if(it == ""){
                    emit(repo.getContaactsRoom())
                }else{
                    emit(repo.getContactsByNameRoom(it))
                }
            }catch (e: Exception){
                Log.d("", e.toString())
                //emit(Resource.Failure(e))
            }
        }
    }
}