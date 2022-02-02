package com.rrohaill.refactoringsample

import com.rrohaill.refactoringsample.api.Result2
import com.rrohaill.refactoringsample.api.MyApi
import com.rrohaill.refactoringsample.data.Place
import com.rrohaill.refactoringsample.data.Places
import com.rrohaill.refactoringsample.domain.PlacesDataSource
import com.rrohaill.refactoringsample.domain.PlacesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

class FetchPlacesUseCaseTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    private val sut = PlacesDataSource(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testUseCase(): Unit = runBlocking {

        val repo: PlacesRepository = mock()

        repo.fetchPlaces(50, 500)
        verify(repo).fetchPlaces(50, 500)

    }

    @Test
    fun `should fetch places correctly given 200 response`() {
        mockWebServer.enqueueResponse("discover-places-200.json", 200)
        runBlocking {
            val actual = sut.fetchActivities(50, 500)

            val expected = Result2.Success(
                Places(
                    listOf(
                        Place(
                            "a353a831-5d53-4cc2-93f3-6c8a3df1e3c9",
                            "Testplats för MO",
                            17.6422735,
                            59.8581518,
                            "Kommer inom kort",
                            "/image/83423179e3a34332857e47f7cacdf703_icon_1416923254311"
                        )
                    ), 1
                )
            )

            assertEquals(expected, actual)

        }
    }

    private fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }

}