package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.data.client.providesOkhttp
import com.picpay.desafio.data.client.providesService
import com.picpay.desafio.data.mapper.UserDataListToUserListMapper
import com.picpay.desafio.data.mapper.UserListToUserDataListMapper
import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.data.source.UserRemoteDataSourceImpl
import com.picpay.desafio.data.source.db.PicPayRoomDatabase
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.data.source.local.UserLocalDataSource
import com.picpay.desafio.data.source.local.UserLocalDataSourceImpl
import com.picpay.desafio.data.source.service.PicPayService
import com.picpay.desafio.data.util.Network
import com.picpay.desafio.data.util.NetworkImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModule {
    private const val OKHTTP = "OKHTTP"

    val dataUtilsModule = module {

        factory<Network> {
            NetworkImpl(
                get()
            )
        }
    }

    val serviceModule = module {

        factory(
            named(OKHTTP)
        ) {
            providesOkhttp()
        }

        factory(
            named(PicPayService::class.java.name)
        ) {
            providesService<PicPayService>(
                get(),
                get(
                    named(OKHTTP)
                )
            )
        }
    }

    val remoteModule = module {

        factory<UserRemoteDataSource>(
            named(UserRemoteDataSource::class.java.name)
        ) {
            UserRemoteDataSourceImpl(
                get(
                    named(PicPayService::class.java.name)
                ),
                get(
                    named(UserResponseListToUserListMapper::class.java.name)
                )
            )
        }
    }

    val localModule = module {
        factory {
            Room.databaseBuilder(
                androidApplication(),
                PicPayRoomDatabase::class.java,
                PicPayRoomDatabase.DB_NAME
            ).build()
        }

        factory(
            named(UserDao::class.java.name)
        ) { get<PicPayRoomDatabase>().userDao() }

        factory<UserLocalDataSource>(
            named(UserLocalDataSource::class.java.name)
        ) {
            UserLocalDataSourceImpl(
                get(
                    named(UserDao::class.java.name)
                ),
                get(
                    named(UserDataListToUserListMapper::class.java.name)
                ),
                get(
                    named(UserListToUserDataListMapper::class.java.name)
                )
            )
        }
    }
}