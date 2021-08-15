package fr.piotrfleury.youcompose.data.sources

import fr.piotrfleury.youcompose.data.models.YoutubeApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("/youtube/v3/videos")
    fun listVideos(
        @Query("key") key: String,
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "fr",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = 50,
    ): Call<YoutubeApiResponse>

    @GET("/youtube/v3/videos")
    fun getVideo(
        @Query("key") key: String,
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "fr",
        @Query("id") id: String,
    ): Call<YoutubeApiResponse>
}