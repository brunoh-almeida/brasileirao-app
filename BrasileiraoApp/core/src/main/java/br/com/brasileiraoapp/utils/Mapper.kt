package br.com.brasileiraoapp.utils

interface Mapper<in E, T> {
    fun mapFrom(from: E): T
}