package com.example.oflinedata

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.oflinedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserAdapter.HandelUserClick {
    lateinit var binding: ActivityMainBinding
    private lateinit var dao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)




        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,"User_DB"
        ).allowMainThreadQueries().build()


         dao = db.getUserDao()


        setAllUser()






        binding.addbtn.setOnClickListener{
            val intent = Intent(this@MainActivity, AddUserActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun setAllUser() {
        dao.getAllData().apply {

            val userAdater = UserAdapter(this@MainActivity,this)
            binding.recyclerview.adapter = userAdater
        }
    }


    override fun onEditClick(user: User) {

        val editIntent = Intent(this@MainActivity, AddUserActivity2::class.java)

        editIntent.putExtra(AddUserActivity2.editKey,user)


        startActivity(editIntent)

    }

    override fun deleteUser(user: User) {

        dao.deleteUser(user)

        Toast.makeText(this, "${user.name}Delete Succesfully", Toast.LENGTH_SHORT).show()
    }

}