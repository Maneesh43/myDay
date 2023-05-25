package com.maneesh.myday.viewmodels

import androidx.lifecycle.ViewModel
import com.maneesh.myday.network.KtorClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {


    private val _quote = MutableStateFlow("")

    val quote
        get() = _quote.asStateFlow()


    suspend fun getQuote() {
        /**
         * Manually mapping chapters to available number shlokas so as to prevent redirections when fetching random shloka
         */
        val chapterShlokas = mapOf(
            1 to (1..47),
            2 to (1..72),
            3 to (1..43),
            4 to (1..42),
            5 to (1..29),
            6 to (1..47),
            7 to (1..30),
            8 to (1..28),
            9 to (1..34),
            10 to (1..42),
            11 to (1..55),
            12 to (1..20),
            13 to (1..35),
            14 to (1..27),
            15 to (1..20),
            16 to (1..24),
            17 to (1..28),
            18 to (1..78)
        )
        val randomChapterNumber = chapterShlokas.keys.random()
        val randomShlokNumber = chapterShlokas[randomChapterNumber]!!.random()
        val shloka = KtorClient.getSloka(
            host = "bhagavadgitaapi.in",
            "/slok/$randomChapterNumber/$randomShlokNumber/"
        )
        _quote.emit(shloka!!.siva.et)
    }


    override fun onCleared() {
        super.onCleared()
        KtorClient.closeConnection()
    }

}