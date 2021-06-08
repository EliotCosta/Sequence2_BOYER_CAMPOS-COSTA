package com.ec.todolist1.model

import com.ec.todolist1.model.ListeToDo

class ProfilListeToDo (var login: String, var mesListeToDo: MutableList<ListeToDo>) {

    fun ajouteListe(uneListe: ListeToDo) {
        this.mesListeToDo.add(uneListe)
    }

}