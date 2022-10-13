package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.data.client.providesOkhttp
import com.picpay.desafio.data.client.providesService
import com.picpay.desafio.data.mapper.UserDataListToUserListMapper
import com.picpay.desafio.data.mapper.UserListToUserDataListMapper
import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.source.GetUsersRemoteDataSource
import com.picpay.desafio.data.source.GetUsersRemoteDataSourceImpl
import com.picpay.desafio.data.source.db.PicPayRoomDatabase
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.data.source.local.GetUsersLocalDataSource
import com.picpay.desafio.data.source.local.GetUsersLocalDataSourceImpl
import com.picpay.desafio.data.source.local.SaveUsersLocalDataSource
import com.picpay.desafio.data.source.local.SaveUsersLocalDataSourceImpl
import com.picpay.desafio.data.source.service.GetUsersService
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
            providesOkhttp(
                get()
            )
        }

        factory(
            named(GetUsersService::class.java.name)
        ) {
            providesService<GetUsersService>(
                get(),
                get(
                    named(OKHTTP)
                )
            )
        }
    }

    val remoteModule = module {

        factory<GetUsersRemoteDataSource>(
            named(GetUsersRemoteDataSource::class.java.name)
        ) {
            GetUsersRemoteDataSourceImpl(
                get(
                    named(GetUsersService::class.java.name)
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
        ) {
            get<PicPayRoomDatabase>().userDao()
        }

        factory<GetUsersLocalDataSource>(
            named(GetUsersLocalDataSource::class.java.name)
        ) {
            GetUsersLocalDataSourceImpl(
                get(
                    named(UserDao::class.java.name)
                ),
                get(
                    named(UserDataListToUserListMapper::class.java.name)
                )
            )
        }

        factory<SaveUsersLocalDataSource>(
            named(SaveUsersLocalDataSource::class.java.name)
        ) {
            SaveUsersLocalDataSourceImpl(
                get(
                    named(UserDao::class.java.name)
                ),
                get(
                    named(UserListToUserDataListMapper::class.java.name)
                )
            )
        }
    }
}