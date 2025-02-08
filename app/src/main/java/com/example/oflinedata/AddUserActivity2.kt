
package com.example.oflinedata

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.oflinedata.databinding.ActivityAddUser2Binding

class AddUserActivity2 : AppCompatActivity() {

    companion object{
        const val editKey = "edit"

        const val update = "Update User"
        const val add = "Add User"
    }


    private lateinit var binding: ActivityAddUser2Binding

    private lateinit var dao: UserDao
    var userId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityAddUser2Binding.inflate(layoutInflater)
        setContentView(binding.root)




        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,"User_DB"
        ).allowMainThreadQueries().build()


         dao = db.getUserDao()


                if (intent.hasExtra(editKey)){

                    binding.BtnAdduserbtn.text = update


                    val user = intent.getParcelableExtra<User>(editKey)

                    user?.let {
                        binding.apply {

                            etUsername.setText(it.name)
                            etUserage.setText(it.age.toString())
                            etUsermobile.setText(it.mobile)

                            userId = it.id
                        } ?:run {
                            Toast.makeText(this, "Missing Data", Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    binding.BtnAdduserbtn.text = add

                }






        binding.BtnAdduserbtn.setOnClickListener{

            val name = binding.etUsername.text.toString()
            val age= binding.etUserage.text.toString()
            val mobile = binding.etUsermobile.text.toString()


            if (binding.BtnAdduserbtn.text.toString()==add){

                addUser(name,age,mobile)

            }else{
                updateUser(name,age,mobile)
            }


        }
    }

    private fun updateUser(name: String,age: String,mobile: String){

        Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show()

        val user = User(userId,name,age.toInt(),mobile)

        dao.updateUser(user)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun addUser(name: String,age: String,mobile: String){


        val user = User(0,name,age.toInt(), mobile)

        dao.adduser(user)


    }
}



