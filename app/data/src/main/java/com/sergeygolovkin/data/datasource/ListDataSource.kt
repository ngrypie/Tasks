package com.sergeygolovkin.data.datasource

/**
 * интерфейс, обозначающий источник списочных данных
 */
internal interface ListDataSource<PARAMS : Any, ENTITY : Any> {

    suspend fun list(params: PARAMS): List<ENTITY>
}