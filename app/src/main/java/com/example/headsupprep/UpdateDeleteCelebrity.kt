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

class UpdateDeleteCelebrity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_celebrity)
        val backButton = findViewById<Button>(R.id.btnBack)
        val celebrityName = findViewById<EditText>(R.id.etName)
        val celebrityTaboo1 = findViewById<EditText>(R.id.etTaboo1)
        val celebrityTaboo2 = findViewById<EditText>(R.id.etTaboo2)
        val celebrityTaboo3 = findViewById<EditText>(R.id.etTaboo3)
        val deleteButton = findViewById<Button>(R.id.btndeleteCel)
        val updateButton = findViewById<Button>(R.id.btnUpdateCel)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val data = intent.extras?.getStringArrayList("data")
        if (data != null) {
            celebrityName.setText(data[1])
            celebrityTaboo1.setText(data[2])
            celebrityTaboo2.setText(data[3])
            celebrityTaboo3.setText(data[4])
        } else {
            Toast.makeText(this, "Sorry null", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {


            apiInterface!!.deleteUser(data!![0].toInt()).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(
                        applicationContext, "The User Has Been Deleted Successfully!!",
                        Toast.LENGTH_SHORT
                    ).show()

                    moveToMainActivity()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Sorry,The User Has Not Been Deleted Successfully!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }

        updateButton.setOnClickListener {
            val pk = data!![0].toInt()
            val name = celebrityName.text.toString()
            val taboo1 = celebrityTaboo1.text.toString()
            val taboo2 = celebrityTaboo2.text.toString()
            val taboo3 = celebrityTaboo3.text.toString()
            apiInterface!!.updateUser(pk, UsersItem(name, pk, taboo1, taboo2, taboo3))
                .enqueue(object :
                    Callback<UsersItem> {
                    override fun onResponse(
                        call: Call<UsersItem>,
                        response: Response<UsersItem>
                    ) {
                        Toast.makeText(
                            applicationContext,
                            "The User Has Been pdated Successfully!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveToMainActivity()
                    }

                    override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Sorry,The User Has Not Been Updated Successfully!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

    fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}