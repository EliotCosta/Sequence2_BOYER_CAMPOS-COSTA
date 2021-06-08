package com.ec.todolist1

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.ec.todolist1.model.ListeToDo
import com.ec.todolist1.model.ListeToDoAdapter
import com.ec.todolist1.model.ProfilListeToDo
import com.google.gson.Gson

class ChoixListActivity : AppCompatActivity(), View.OnClickListener, ListeToDoAdapter.ActionListener {

    private lateinit var listeToDoAdapter: ListeToDoAdapter

    private lateinit var pseudo: String
    private lateinit var btn: Button
    private lateinit var nouvelleList: EditText
    private lateinit var getJson: String
    private lateinit var gson: Gson
    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var profil: ProfilListeToDo
    private lateinit var recyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choixlist_layout)

        val btnOK = findViewById<Button>(R.id.button)
        val edtListe = findViewById<EditText>(R.id.mdp)
        val recyclerview = findViewById<RecyclerView>(R.id.listerecycler)
        listeToDoAdapter =
            ListeToDoAdapter(actionListener = object :
                ListeToDoAdapter.ActionListener {
                override fun onItemClicked(listeToDo: ListeToDo) {

                    val bdl = Bundle()
                    bdl.putString("pseudo", pseudo)
                    bdl.putString("nom_liste", listeToDo.titreListeToDo)

                    // vers la ShowListActivity correspondante
                    val toShow = Intent(this@ChoixListActivity, ShowListActivity::class.java)
                    toShow.putExtras(bdl)
                    startActivity(toShow)

                }


            })
        recyclerview.adapter = listeToDoAdapter


        btnOK?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button2 -> {
                profil.mesListeToDo.add(ListeToDo(nouvelleList.text.toString(), mutableListOf()))
                getJson = gson.toJson(profil)

                editor.putString(pseudo, getJson)
                editor.commit()
                recyclerView.adapter = ListeToDoAdapter(
                    this
                )
            }

        }
    }

    override fun onItemClicked(listeToDo: ListeToDo) {
        TODO("Not yet implemented")
    }

}