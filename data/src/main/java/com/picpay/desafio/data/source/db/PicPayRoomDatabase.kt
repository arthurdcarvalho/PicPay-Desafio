package com.picpay.desafio.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.data.model.local.UserData

@Database(
    entities = [UserData::class],
    version = 1,
    exportSchema = false
)
abstract class PicPayRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "picpay-db"
    }
}
