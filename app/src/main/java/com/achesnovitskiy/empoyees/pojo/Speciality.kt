package com.achesnovitskiy.empoyees.pojo

import com.google.gson.annotations.SerializedName

data class Speciality (
    @SerializedName("specialty_id") val specialty_id : Int,
    @SerializedName("name") val name : String
)