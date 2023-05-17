package com.juliopicazo.todolist.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class AppModule {
    @Module(
        includes = [
            DataBaseModule::class,
            UseCaseModule::class
        ]
    )
    @InstallIn(SingletonComponent::class)
    class AppModule
}