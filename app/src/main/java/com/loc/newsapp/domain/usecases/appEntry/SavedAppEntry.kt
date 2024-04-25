package com.loc.newsapp.domain.usecases.appEntry

import com.loc.newsapp.domain.manager.LocalManager

class SavedAppEntry(
    private val localManager: LocalManager
) {
    suspend operator fun invoke() {
        localManager.savedAppEntry()
    }
}