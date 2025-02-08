package com.example.oflinedata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {
    @Insert
    fun adduser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)


    @Query("SELECT*FROM User")
    fun getAllData(): List<User>
}