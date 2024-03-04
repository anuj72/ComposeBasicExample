package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class MyData(val txt: String, val isCheck: Boolean)

class MainVm() : ViewModel() {
    private val _listFlow = MutableStateFlow<List<MyData>>(emptyList())
    val listFlow = _listFlow.asStateFlow()


    init {
        _listFlow.value = listOf(
            MyData(txt = "CAT1", isCheck = false),
            MyData(txt = "CAT2", isCheck = false),
            MyData(txt = "CAT3", isCheck = false)
        )
    }

    fun doCheck(pos: Int) {
        val l1 = _listFlow.value.toMutableList()
        l1[pos] = l1[pos].copy(isCheck = !l1[pos].isCheck)
        _listFlow.update {
            l1
        }
    }


}


