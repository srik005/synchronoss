package com.srikanth.synchronoss.repository

import com.srikanth.synchronoss.model.Weather
import com.srikanth.synchronoss.retrofit.RetrofitService
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.Test
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AppRepoTest {
    @Test
    fun testApiResponse() {
        val mockedApiInterface: RetrofitService = Mockito.mock(RetrofitService::class.java)
        val mockedCall: Call<Weather> = Mockito.mock<Call<*>>(Call::class.java) as Call<Weather>
        Mockito.`when`(mockedApiInterface.getData("12", "34", "abcdefghjj")).thenReturn(mockedCall)
        Mockito.doAnswer {
          invocation->
            invocation.getArgument<Weather>(0)
        }.`when`(mockedCall).enqueue(any(Callback::class.java))
    }
}