package com.picpay.desafio.data.mapper

interface Mapper<CLASS_IN, CLASS_OUT> {
    fun transform(classIn: CLASS_IN?): CLASS_OUT
}