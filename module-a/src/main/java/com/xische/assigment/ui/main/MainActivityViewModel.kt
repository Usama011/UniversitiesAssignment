package com.xische.assigment.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.university.entity.UniversityEntity
import com.xische.assigment.domain.university.usecase.FetchUniversityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchUniversityUseCase: FetchUniversityUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<MainActivityState>(MainActivityState.Init)
    val state: StateFlow<MainActivityState> get() = _state


    private val _universities = MutableStateFlow(mutableListOf<UniversityEntity>())
    val universities: StateFlow<List<UniversityEntity>> get() = _universities

    private fun setLoading() {
        _state.value = MainActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = MainActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = MainActivityState.ShowToast(message)
    }

    fun fetchUniversities() {
        viewModelScope.launch {
            fetchUniversityUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch { e ->
                    hideLoading()
                    showToast(e.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when (result) {
                        is BaseResult.Success -> {
                            _universities.value = result.data as MutableList<UniversityEntity>
                        }

                        is BaseResult.Error -> {
                            // 0 means no internet connection
                            val msg = "${result.err.message} [${result.err.code}]"
                            showToast(msg)
                        }
                    }
                }
        }
    }
}

sealed class MainActivityState {
    object Init : MainActivityState()
    data class ShowToast(val message: String) : MainActivityState()
    data class IsLoading(val isLoading: Boolean) : MainActivityState()
}