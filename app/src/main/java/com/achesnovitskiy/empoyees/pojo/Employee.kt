package com.achesnovitskiy.empoyees.pojo

import com.google.gson.annotations.SerializedName

data class Employee (
    @SerializedName("f_name") val f_name : String,
    @SerializedName("l_name") val l_name : String,
    @SerializedName("birthday") val birthday : String? = null,
    @SerializedName("avatr_url") val avatr_url : String? = null,
    @SerializedName("specialty") val specialty : List<Speciality>? = null
)