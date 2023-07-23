package com.advanced.base.repository

import com.advanced.base.api.MemeApi
import com.advanced.base.models.MemeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class MemeRepository @Inject constructor(val memeApi: MemeApi) {

    private val _meme = MutableStateFlow<MemeModel>(MemeModel("",""))
    val meme : StateFlow<MemeModel>
        get() = _meme

    suspend fun getRandomMeme(){
        val result = memeApi.getMeme()
        if(result.isSuccessful && result.body()!=null){
            _meme.emit(result.body()!!)
        }
    }
}