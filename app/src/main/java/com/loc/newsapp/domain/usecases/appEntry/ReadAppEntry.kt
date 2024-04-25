package com.loc.newsapp.domain.usecases.appEntry

import com.loc.newsapp.domain.manager.LocalManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry  (private val localManager: LocalManager
) {
     operator fun invoke(): Flow<Boolean> {
       return localManager.readAppEntry()
    }

}