package com.example.darkskydestinations.Networking

import com.example.darkskydestinations.Models.Event

interface EventsInterface {
    fun getInitialEventsList(): ArrayList<Event>;
}