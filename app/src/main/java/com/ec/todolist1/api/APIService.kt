package com.ec.todolist1.api

import com.ec.todolist1.model.ListeToDoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface APIService {
    @GET("users?pseudo={pseudo}&pass={mdp}")
    suspend fun checkUser(@Path("pseudo") pseudo: String, @Path("mdp") mdp: String ): Boolean


    @GET("users/2/lists")
    suspend fun getListeListesToDo(): ListeToDoResponse

    /*
    @GET("lists/3/items")
    suspend fun getListeItemToDo(): ItemToDoResponse
    */

}
