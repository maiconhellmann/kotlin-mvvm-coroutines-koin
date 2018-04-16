package com.maiconhellmann.architecture.data

import com.maiconhellmann.architecture.data.local.MovieDatabase
import com.maiconhellmann.architecture.data.model.Type
import com.maiconhellmann.architecture.data.remote.dto.SearchMovieDto
import com.maiconhellmann.architecture.data.remote.endpoint.MovieWebService

class MovieRepository(private val remoteDataSource: MovieWebService,
                      private val localDataSource: MovieDatabase) {

    suspend fun searchMovies(query: String): SearchMovieDto {
        //remote data source Request
        return remoteDataSource.getMovies(query, Type.MOVIE.value).await()
    }

    suspend fun searchEpisodes(query: String): SearchMovieDto {
        //remote data source Request
        return remoteDataSource.getMovies(query, Type.EPISODE.value).await()
    }

    suspend fun searchSeries(query: String): SearchMovieDto {
        //remote data source Request
        return remoteDataSource.getMovies(query, Type.SERIES.value).await()
    }

    suspend fun queryDatabase() {
        //Database query example
//        val sizeDeffered = async {
//            localDataSource.rateDao().getRates().size
//        }
//        Timber.e("erro: ${sizeDeffered.await()}")
    }
}