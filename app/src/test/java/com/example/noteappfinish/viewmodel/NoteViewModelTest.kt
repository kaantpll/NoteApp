package com.example.noteappfinish.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.noteappfinish.MainCoroutineRule
import com.example.noteappfinish.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var maniCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup() {
        viewModel = NoteViewModel(FakeNoteRepository())
    }
}