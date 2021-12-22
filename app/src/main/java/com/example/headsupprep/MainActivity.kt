package com.example.headsupprep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var listItem : List<UsersItem>
    private lateinit var addButton : Button
    private lateinit var submitButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.btnAdd)
        submitButton = findViewById(R.id.btnSubmit)
        val celebrityName =findViewById<EditText>(R.id.etCelName)
        recyclerView = findViewById(R.id.rvMain)


        submitButton.setOnClickListener {
            var founded : UsersItem? = null
            val celebrityText = celebrityName.text.toString()

            for (i in listItem){
                if (celebrityText == i.name){
                    founded = i
                    break
                }
            }
            if (founded != null){

                val intent = Intent(this, UpdateDeleteCelebrity::class.java)

                intent.putStringArrayListExtra(
                    "data", arrayListOf(
                        founded.pk.toString(),
                        founded.taboo1,
                        founded.taboo2,
                        founded.taboo3
                    )
                )
                startActivity(intent)
            }
            else {
                Toast.makeText(
                    this, "Sorry , Its Not Found. Go To Add A New One ", Toast.LENGTH_SHORT).show()
                val  intent = Intent(this , NewCelebrity::class.java)
                startActivity(intent)
            }
        }
        addButton.setOnClickListener {
            val intent = Intent(this , NewCelebrity ::class.java)
            startActivity(intent)
        }
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
    apiInterface!!.getAPIUsers().enqueue(object : Callback<List<UsersItem>>{

        override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
            call.cancel()
        }

        override fun onResponse(call: Call<List<UsersItem>>, response: Response<List<UsersItem>>) {
            val list = response.body()!!
            updateView(list)

        }
    })

    }

    private fun updateView(list: List<UsersItem>){
        listItem = list
        recyclerView.adapter = RVAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
