package fr.piotrfleury.youcompose.repositories

import android.util.Log
import fr.piotrfleury.youcompose.core.GOOGLE_API_URL
import fr.piotrfleury.youcompose.data.models.Video
import fr.piotrfleury.youcompose.data.models.YoutubeApiResponse
import fr.piotrfleury.youcompose.data.sources.YoutubeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object VideosRepository {

    private val retrofit: Retrofit = buildRetrofit()

    private val youtubeApi: YoutubeApi = retrofit.create(YoutubeApi::class.java)

    var videos = listOf<Video>()

    private fun buildRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit
            .Builder()
            .baseUrl(GOOGLE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun listVideos(
        key: String,
        onSuccess: (List<Video>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        if(videos.isNotEmpty()) {
            onSuccess(videos)
        } else {
            youtubeApi.listVideos(key = key).enqueue(object : Callback<YoutubeApiResponse> {
                override fun onResponse(
                    call: Call<YoutubeApiResponse>,
                    response: Response<YoutubeApiResponse>
                ) {
                    if (response.isSuccessful) {
                        videos = response.body()?.items ?: listOf()
                        onSuccess(videos)
                    } else {
                        onError(Throwable("Unsuccessful response"))
                    }
                }

                override fun onFailure(call: Call<YoutubeApiResponse>, t: Throwable) {
                    onError(t)
                }

            })
        }
    }

    fun getVideo(
        videoId: String,
        key: String,
        onSuccess: (Video?) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        youtubeApi.getVideo(key = key, id = videoId)
            .enqueue(object : Callback<YoutubeApiResponse> {
                override fun onResponse(
                    call: Call<YoutubeApiResponse>,
                    response: Response<YoutubeApiResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()?.items?.first())
                    } else {
                        Log.e("[VIDEO_REPOSITORY]","getVideo($videoId) returned status ${response.code()}")
                        onError(Throwable("Unsuccessful response"))
                    }
                }

                override fun onFailure(call: Call<YoutubeApiResponse>, t: Throwable) {
                    onError(t)
                }

            })
    }
}