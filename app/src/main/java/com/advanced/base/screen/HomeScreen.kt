package com.advanced.base.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.advanced.base.components.Controls
import com.advanced.base.components.topBar
import com.advanced.base.models.MemeModel
import com.advanced.base.viewmodel.MemeViewModel

@Composable
fun HomeScreen(context: Context) {

    val memeViewModel: MemeViewModel = viewModel()
    memeViewModel.getRandomMeme()
    val meme: State<MemeModel> = memeViewModel.meme.collectAsState()

    Column() {
        topBar()
        Controls(
            meme.value.title!!,
            meme.value.url!!,
            loadNewMeme = { memeViewModel.getRandomMeme() },context)
    }
}