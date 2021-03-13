package com.example.noteappfinish.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.noteappfinish.getOrAwaitValue
import com.example.noteappfinish.model.Note
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class NoteDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database : NoteDatabase


    private lateinit var dao : NoteDao

    @Before
    fun setup(){
       /* database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),NoteDatabase::class.java
        ).allowMainThreadQueries().build()
       */


        hiltRule.inject()
        dao = database.dao()
    }

    @After
    fun finish(){
        database.close()
    }

    @Test
    fun insertNote() = runBlocking{
        val note = Note("Title","Body",1)
        dao.insertNote(note)

        val list = dao.getAllNote().getOrAwaitValue()
        assertThat(list).contains(note)
    }

    @Test
    fun deleteNote() = runBlocking {
        val note = Note("Title","Body",1)
        dao.insertNote(note)
        dao.deleteNote(note)

        val list = dao.getAllNote().getOrAwaitValue()
        assertThat(list).doesNotContain(list)
    }

}