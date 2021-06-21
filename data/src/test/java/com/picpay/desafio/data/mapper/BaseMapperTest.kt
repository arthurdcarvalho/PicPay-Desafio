package com.picpay.desafio.data.mapper

import com.picpay.desafio.data.di.MapperModules
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

abstract class BaseMapperTest<CLASS_IN, CLASS_OUT>(name: String) : AutoCloseKoinTest() {

    open val mapper: Mapper<CLASS_IN, CLASS_OUT> by inject(named(name))

    @Before
    fun setUpKoin() {
        startKoin {
            modules(MapperModules.mapperModules)
        }
    }

    @Test
    open fun mapClassInToClassOut() {
        assertEquals(classOut(), mapper.transform(classIn()))
    }

    @Test
    open fun mapClassNullInToClassNullOut() {
        assertNull(mapper.transform(null))
    }

    abstract fun classIn(): CLASS_IN

    abstract fun classOut(): CLASS_OUT
}