package com.advanced.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advanced.base.models.MemeModel
import com.advanced.base.repository.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(val memeRepository: MemeRepository) : ViewModel() {

    val meme : StateFlow<MemeModel>
        get() = memeRepository.meme

    fun getRandomMeme(){
        viewModelScope.launch {
            memeRepository.getRandomMeme()
        }
    }
}