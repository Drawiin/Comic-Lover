package com.example.comiclover.di

import com.example.comiclover.domain.boundaries.ComicRepository
import com.example.comiclover.repository.DefaultComicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsComicRepository(defaultComicRepository: DefaultComicRepository): ComicRepository
}