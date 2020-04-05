package com.achesnovitskiy.empoyees.api

import com.achesnovitskiy.empoyees.pojo.EmployeeResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("testTask.json")
    fun getEmployees(): Observable<EmployeeResponse>
}