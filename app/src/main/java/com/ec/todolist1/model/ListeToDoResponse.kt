package com.ec.todolist1.model

import com.google.gson.annotations.SerializedName


data class ListeToDoResponse(
    @SerializedName("listeListesToDo")
    val listeListesToDo :List<ListeToDo>)