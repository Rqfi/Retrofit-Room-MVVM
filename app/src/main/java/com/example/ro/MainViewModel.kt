package com.example.ro

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _adapterData = MutableLiveData<List<Character>>()
    val adapterData: LiveData<List<Character>> get() = _adapterData

    private val _filteredData = MutableLiveData<List<Character>>()
    val filteredData: LiveData<List<Character>> get() = _filteredData

    private lateinit var db: AppDatabase

    fun setupDatabase(context: Context){
        db = AppDatabase.getDatabase(context)
    }

    fun fetchData(){
        ApiConfig.getService().getData().enqueue(object : Callback<Responses> {
            override fun onResponse(call: Call<Responses>, response: Response<Responses>) {
                if (response.isSuccessful){
                    response.body()?.results?.let {
                        saveDataToRoom(it)
                    }
                }
            }

            override fun onFailure(call: Call<Responses>, t: Throwable) {

            }
        })

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _filteredData.postValue(db.appDao().showAllData())
            }
        }
    }

    private fun saveDataToRoom(data: List<ResultsItem>){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.appDao().deleteAllData()
                db.appDao().insertData(data.map {
                    Character(
                        name = it.name,
                        height = it.gender,
                        birthYear = it.birthYear,
                        gender = it.gender
                    )
                })
            }
        }
    }

    fun filterData(searchText: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data = db.appDao().searchData("%$searchText%")
                _filteredData.postValue(data)
            }
        }
    }
}