package com.picpay.desafio.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.data.model.local.UserData

@Dao
interface UserDao : BaseDao<UserData> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserList(list: List<UserData>)

    @Query("SELECT * FROM T_USER")
    suspend fun getUserList(): List<UserData>
}