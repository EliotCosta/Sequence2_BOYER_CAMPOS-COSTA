package com.ec.todolist1.data


import android.content.Context
import android.preference.PreferenceManager
import com.ec.todolist1.api.APIService
import com.ec.todolist1.model.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataProvider(context: Context) {

    val sp = PreferenceManager.getDefaultSharedPreferences(context)

    private val BASE_URL = sp.getString("URL API", "http://tomnab.fr/todo-api/").toString()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val service = retrofit.create(APIService::class.java)


    suspend fun getListFromApi(): List<ListeToDo> {
        return service.getListeListesToDo().listeListesToDo
    }
/*
    suspend fun getItemFromApi(): List<ItemToDo> {
        return service.getListeItemsToDo().listeItemToDo
    }
*/
}