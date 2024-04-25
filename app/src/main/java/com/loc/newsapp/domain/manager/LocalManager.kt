package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocalManager {
    suspend fun savedAppEntry()
    fun readAppEntry() :Flow<Boolean>
}