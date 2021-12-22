package com.example.headsupprep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewCelebrity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_celebrity)
        val celebrityName = findViewById<EditText>(R.id.etName)
        val celebrityTaboo1 = findViewById<EditText>(R.id.etTaboo1)
        val celebrityTaboo2 = findViewById<EditText>(R.id.etTaboo2)
        val celebrityTaboo3 = findViewById<EditText>(R.id.etTaboo3)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val addButton = findViewById<Button>(R.id.btnAdd)
        val backButton = findViewById<Button>(R.id.btnBack)
        addButton.setOnClickListener {
            val pk = 1
            val name = celebrityName.text.toString()
            val taboo1 = celebrityTaboo1.text.toString()
            val taboo2 = celebrityTaboo2.text.toString()
            val taboo3 = celebrityTaboo3.text.toString()

            apiInterface!!.addUser(UsersItem(name, pk, taboo1, taboo2, taboo3)).enqueue(object :
                Callback<UsersItem> {
                override fun onResponse(
                    call: Call<UsersItem>,
                    response: Response<UsersItem>
                ) {
                    Toast.makeText(
                        applicationContext, "It Has been Successfully !",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Sorry, It Has been Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            )
        }
        backButton.setOnClickListener {
            goToMainActivity()
        }
    }

    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

}