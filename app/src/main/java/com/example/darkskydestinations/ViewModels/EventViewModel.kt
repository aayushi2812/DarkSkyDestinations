package com.example.darkskydestinations.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.darkskydestinations.Models.Event

class EventViewModel() : ViewModel() {

    var repo : AppRepository = AppRepository()

    var stateList : SnapshotStateList<Event> = mutableStateListOf<Event>().apply {
        addAll(repo.getInitialEventsList())
    }


}