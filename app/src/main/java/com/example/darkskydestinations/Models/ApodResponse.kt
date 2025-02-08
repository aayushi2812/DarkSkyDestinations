package com.example.darkskydestinations.Models

data class ApodResponse(
    val copyright: String?,
    val date: String,
    val explanation: String,
    val media_type: String,
    val title: String,
    val url: String
)