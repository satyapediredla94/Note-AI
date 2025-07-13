package com.example.noteaiagent.di

import android.app.Application
import androidx.room.Room
import com.example.noteaiagent.data.AIAgentRepoImpl
import com.example.noteaiagent.data.KoogAgent
import com.example.noteaiagent.data.repo.NoteRepositoryImpl
import com.example.noteaiagent.data.repo.db.NoteDatabase
import com.example.noteaiagent.domain.AIAgentRepo
import com.example.noteaiagent.domain.AddNoteUseCase
import com.example.noteaiagent.domain.NoteRepository
import com.example.noteaiagent.domain.QueryNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAddNoteUseCase(
        agentRepo: AIAgentRepo,
        noteRepository: NoteRepository
    ): AddNoteUseCase {
        return AddNoteUseCase(
            agentRepo = agentRepo,
            noteRepository = noteRepository
        )
    }

    @Provides
    @Singleton
    fun provideQueryNotesUseCase(
        agentRepo: AIAgentRepo,
        noteRepository: NoteRepository
    ): QueryNotesUseCase {
        return QueryNotesUseCase(
            aiAgentRepo = agentRepo,
            noteRepository = noteRepository
        )
    }

    @Provides
    @Singleton
    fun provideAIAgentRepo(): AIAgentRepo {
        return AIAgentRepoImpl(KoogAgent())
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }
}