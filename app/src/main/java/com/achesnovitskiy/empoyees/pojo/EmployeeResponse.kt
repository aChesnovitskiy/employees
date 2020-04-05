package com.achesnovitskiy.empoyees.pojo

import com.google.gson.annotations.SerializedName

data class EmployeeResponse (
    @SerializedName("response") val employees : List<Employee>
)