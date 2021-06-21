package com.picpay.desafio.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.data.model.local.UserData

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
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
