package com.loc.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) :ViewModel(){
    fun onEvent(event: OnBoardingEvent) {
        when(event){
            is OnBoardingEvent.SavedAppEntry->{
                saveAppEntry()
            }
        }
    }
    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.savedAppEntry
        }
    }
}

