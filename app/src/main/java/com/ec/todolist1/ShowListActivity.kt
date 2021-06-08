package com.ec.todolist1

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.ec.todolist1.model.ItemAdapter
import com.ec.todolist1.model.ItemToDo
import com.ec.todolist1.model.ListeToDo
import com.ec.todolist1.model.OnItemClickListener
import com.google.gson.Gson

class ShowListActivity : AppCompatActivity(), View.OnClickListener, OnItemClickListener {

    private lateinit var pseudo: String
    private lateinit var btn: Button
    private lateinit var newItem: EditText

    private lateinit var nom: String

    private lateinit var getJson: String
    private lateinit var gson: Gson

    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var liste: ListeToDo
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.showlist_layout)

        newItem = findViewById(R.id.nouvelItem)
        btn = findViewById(R.id.button3)
        btn.setOnClickListener(this)


        val bdl = this.intent.extras
        pseudo = bdl!!.getString("pseudo")!!
        nom = bdl.getString("nom_liste")!!.replace(" ", "_")

        sp = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sp.edit()
        getJson = sp.getString(Pair(pseudo, nom).toString(),
            "{\"nom\": $nom\", \"items\": []}").toString()

        gson = Gson()
        liste = gson.fromJson(getJson, ListeToDo::class.java)

        recyclerView = findViewById(R.id.itemrecycler)
        recyclerView.adapter = ItemAdapter(liste.itemToDo, this)

    }

    override fun onClick(v: View){
        when (v.id) {
            R.id.button3 -> {
                liste.itemToDo.add(ItemToDo(newItem.text.toString(), false))

                getJson = gson.toJson(liste)
                editor.putString(Pair(pseudo, nom).toString(), getJson)
                editor.commit()

                recyclerView.adapter = ItemAdapter(liste.itemToDo, this)
            }
        }
    }

    override fun onItemClicked(v: View, pos: Int) {
        val checkBox = v as CheckBox
        val item = liste.itemToDo[pos]
        item.fait = checkBox.isChecked

        getJson = gson.toJson(liste)
        editor.putString(Pair(pseudo, nom).toString(), getJson)
        editor.commit()

    }

}