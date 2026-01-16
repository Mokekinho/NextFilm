package com.example.nextfilm.ui.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.data.repository.NextFilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import com.example.nextfilm.data.models.MediaListEntry
import com.example.nextfilm.data.sources.remote.TimeWindow
import com.example.nextfilm.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class TvState(
    val trendingTvList: List<MediaListEntry> = emptyList(),
    val popularTvList: List<MediaListEntry> = emptyList(),
    val topRatedTvList: List<MediaListEntry> = emptyList(),
    val timeWindow: TimeWindow = TimeWindow.DAY,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
)

@HiltViewModel // vou injetatar coisas aqui
class TvViewModel @Inject constructor(
    private val repository: NextFilmRepository
) : ViewModel() {
    private val logTag = "TvViewModel"
    private val defaultErrorMessage = "Error on Load Data"

    private val _state = MutableStateFlow(TvState())
    val state: StateFlow<TvState> = _state.asStateFlow()

    init {
        loadData()
    }

    private suspend fun loadTrendingTvList() {

        val result = repository.getTrendingMediaList(
            mediaType = "tv",
            timeWindow = _state.value.timeWindow,
            page = 1,
        )

        when (result) {

            is Resource.Success -> {

                _state.update {
                    it.copy(
                        trendingTvList = it.trendingTvList + result.data!!.results.map { res ->
                            MediaListEntry(
                                res.title ?: "",
                                res.posterPath ?: "",
                                res.id,
                                mediaType = res.mediaType?: ""
                            )
                        }
                    )
                }


            }

            is Resource.Error -> {
                throw Exception(result.message ?: defaultErrorMessage)
            }

            is Resource.Loading -> Unit

        }



    }
    private suspend fun loadPopularTvList() {
        val result = repository.getPopularTvList(
            page = 1,
        )

        when (result) {

            is Resource.Success -> {

                _state.update {
                    it.copy(
                        popularTvList = it.popularTvList + result.data!!.results.map { res ->
                            MediaListEntry(
                                res.title ?: "",
                                res.posterPath ?: "",
                                res.id,
                                mediaType = "tv"
                            )
                        }
                    )
                }
            }

            is Resource.Error -> {
                throw Exception(result.message ?: defaultErrorMessage) // to lançando uma excessão
            }

            is Resource.Loading -> Unit

        }
    }
    private suspend fun loadTopRatedTvList() {
        val result = repository.getTopRatedTvList(
            page = 1,
        )

        when (result) {

            is Resource.Success -> {

                _state.update {
                    it.copy(
                        topRatedTvList = it.topRatedTvList + result.data!!.results.map { res ->

                            Log.d(logTag, "Mapeando o filme =${res.title}")
                            MediaListEntry(
                                movieName = res.title ?: "",
                                imageUrl = res.posterPath ?: "",
                                id = res.id,
                                mediaType = "tv"
                            )
                        }
                    )
                }
            }

            is Resource.Error -> {
                throw Exception(result.message?: defaultErrorMessage)
            }

            is Resource.Loading -> Unit

        }



    }

    fun loadData(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isLoaded = false,
                    error = null,
                )
            }
            try{

                loadTrendingTvList()
                loadPopularTvList()
                loadTopRatedTvList()

                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoaded = true,
                        error = null,
                    )
                }

            }
            catch (e: Exception){
                _state.update {
                    it.copy(
                        error = e.message,
                        isLoading = false,
                        isLoaded = false
                    )
                }
            }
        }
    }
}