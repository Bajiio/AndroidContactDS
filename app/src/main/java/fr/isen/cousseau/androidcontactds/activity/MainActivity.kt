package fr.isen.cousseau.androidcontactds.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.cousseau.androidcontactds.R
import fr.isen.cousseau.androidcontactds.adapter.MainAdapter
import fr.isen.cousseau.androidcontactds.data.model.Contact
import fr.isen.cousseau.androidcontactds.data.model.Result
import fr.isen.cousseau.androidcontactds.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var url="https://randomuser.me/api/?results=10"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Contacts"

        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = MainAdapter(arrayOf()) {
            onSetContact(it)
        }
        addData()


    }

    private fun addData(){
        val queue= Volley.newRequestQueue(this)
        val jsonObjectID = JSONObject()
        val gson= Gson()


        val req = JsonObjectRequest(Request.Method.GET, url, jsonObjectID, {
                response ->
            val dataFromJSON:Result= gson.fromJson(response.toString(),Result::class.java)
            Log.v("data added",dataFromJSON.results[1].toString())

            val adapter = binding.rvList.adapter as MainAdapter
            adapter.updateData(dataFromJSON)
        }, {
                errorData -> Log.e("ERROR", errorData.toString())
        })

        queue.add(req)
    }

    private fun onSetContact(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra("contact",contact)
        startActivity(intent)
    }
}