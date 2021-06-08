package com.ec.todolist1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.ec.todolist1.ChoixListActivity as versChoixListActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private final var sp: SharedPreferences? = null
    private var edtPseudo: EditText? = null
    private final var btnOK: Button? = null
    private var editor: Editor? = null
    private var edtMdp: EditText? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOK = findViewById<Button>(R.id.button)
        edtPseudo = findViewById<EditText>(R.id.pseudo)
        edtMdp = findViewById<EditText>(R.id.mdp)

        sp = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sp?.edit()

        // Demande à MainActivity d'implémenter l'interface onClickListener

        // Demande à MainActivity d'implémenter l'interface onClickListener
        btnOK?.setOnClickListener(this)
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.layout.menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()

        // Relire les préférences partagées de l'application
        val cbR: Boolean = sp!!.getBoolean("remember", false)


        // SI la case est cochée, on utilise les préférences pour définir le login
        if (cbR) {
            val l: String = sp?.getString("login", "login inconnu").toString()
            edtPseudo!!.setText(l)
        } else {
            // Sinon, le champ doit être vide
            edtPseudo!!.setText("")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.menu_pref -> {
                val iSettings = Intent(this, SettingsActivity::class.java)
                startActivity(iSettings)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {




                editor!!.putString("login", edtPseudo!!.text.toString())
                editor!!.commit()

                // Fabrication d'un Bundle de données
                val bdl = Bundle()
                bdl.putString("json", edtPseudo!!.text.toString())
                // Changer d'activité

                // Intent explicite
                var versChoixListActivity: Intent = Intent(this@MainActivity, versChoixListActivity::class.java)
                // Ajout d'un bundle à l'intent
                val putExtras = versChoixListActivity.putExtras(bdl)
                startActivity(versChoixListActivity)
            }
        }
    }

}