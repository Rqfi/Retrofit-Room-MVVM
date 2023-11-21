package com.example.ro

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository
) : ViewModel() {

    private val _filteredData = MutableLiveData<List<Character>>()
    val filteredData: LiveData<List<Character>> get() = _filteredData

    private lateinit var db: AppDatabase

    fun setupDatabase(context: Context){
        db = AppDatabase.getDatabase(context)
    }

    fun fetchData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchData()
                _filteredData.postValue(db.appDao().showAllData())
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