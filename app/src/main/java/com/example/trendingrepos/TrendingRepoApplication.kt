package com.example.trendingrepos


import android.app.Application
import com.example.trendingrepos.data.AppContainer
import com.example.trendingrepos.data.DefaultAppContainer

class TrendingRepoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= DefaultAppContainer()
    }
}