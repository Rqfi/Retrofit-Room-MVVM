package com.example.ro

import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao,
) {
    fun ResultsItem.toCharacter(): Character {
        return Character(
            name = this.name,
            height = this.height,
            birthYear = this.birthYear,
            gender = this.gender
        )
    }

    fun Character.toResultsItem(): ResultsItem {
        return ResultsItem(
            name = this.name,
            height = this.height,
            birthYear = this.birthYear,
            gender = this.gender
        )
    }

    suspend fun fetchData(): List<ResultsItem> {
        val response : Response<Responses> = apiService.getData().execute()
        if (response.isSuccessful) {
            val data = response.body()?.results
            data?.let {
                this.saveDataToRoom(it)
            }
        }
        return appDao.showAllData().map { ResultsItem() }
    }

    private suspend fun saveDataToRoom(data: List<ResultsItem>) {
        appDao.deleteAllData()
        appDao.insertData(data.map { it.toCharacter() })
    }
}